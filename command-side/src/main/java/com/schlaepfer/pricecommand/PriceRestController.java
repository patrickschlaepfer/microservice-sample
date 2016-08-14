package com.schlaepfer.pricecommand;

import com.schlaepfer.pricecommand.commands.AddPriceCommand;
import com.schlaepfer.utils.Asserts;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@RestController
@RequestMapping("/prices")
public class PriceRestController {

    private static final Logger LOG = LoggerFactory.getLogger(PriceRestController.class);

    @Autowired
    CommandGateway commandGateway;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void add(@PathVariable(value = "id") String id,
                    @RequestParam(value = "name", required = true) String name,
                    HttpServletResponse response) {

        LOG.debug("Adding Price [{}] '{}'", id, name);

        try {
            Asserts.INSTANCE.areNotEmpty(Arrays.asList(id, name));
            AddPriceCommand command = new AddPriceCommand(id, name);
            commandGateway.sendAndWait(command);
            LOG.info("Added Price [{}] '{}'", id, name);
            response.setStatus(HttpServletResponse.SC_CREATED);// Set up the 201 CREATED response
            return;
        } catch (AssertionError ae) {
            LOG.warn("Add Request failed - empty params?. [{}] '{}'", id, name);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (CommandExecutionException cex) {
            LOG.warn("Add Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOG.warn("A duplicate price with the same ID [{}] already exists.", id);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }
}
