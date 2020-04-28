package variamos.services;

import com.variamos.moduino.binder.api.BinderAPI;
import me.itoxic.moduino.metamodel.arduino.entries.Project;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@EnableAutoConfiguration
public class ArduinoGenerator {

    @CrossOrigin
    @RequestMapping(value="/json/test-api", method= RequestMethod.POST, produces="text/plain")
    @ResponseBody
    String generateCode(@RequestBody String data_collected) {

        // default response
        String response = "Unknow Error";

        try {

            // create dynamic project with the collected data (JSON)
            Project project = BinderAPI.getDynamicProject(data_collected);

            // generate arduino code
            StringBuilder sketchCode = project.getBoards().get(0).generateCode().getBufferCode();

            // set response
            response = sketchCode.toString();

        } catch (Exception e) {

            // instantiate stringwriter
            StringWriter sw = new StringWriter();

            // print all the stacktrace into the stringwriter
            e.printStackTrace(new PrintWriter(sw));

            // set error as response
            response = sw.toString();

        }

        // return the response
    	return response;

    }

}
