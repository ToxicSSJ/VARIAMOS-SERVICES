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
    @RequestMapping(value="/CodeGenerator/Arduino", method= RequestMethod.POST, produces="text/plain")
    @ResponseBody
    String generateCode(@RequestBody String data_collected) {

        String response = "Unknow Error";

        try {

            Project project = BinderAPI.getDynamicProject(data_collected);
            StringBuilder sketchCode = project.getBoards().get(0).generateCode().getBufferCode();

            response = sketchCode.toString();

        } catch (Exception e) {

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            response = sw.toString();

        }

    	return response;

    }

}
