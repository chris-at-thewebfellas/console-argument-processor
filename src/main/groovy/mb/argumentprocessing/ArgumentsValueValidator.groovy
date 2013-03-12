package mb.argumentprocessing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import mb.argumentprocessing.argumentprocessors.ArgumentFetcher

@Component
class ArgumentsValueValidator {

    @Autowired
    ArgumentFetcher argumentFetcher

    boolean isValid(List<String> suppliedKnownArgs, List<String> arguments) {

        try {

            suppliedKnownArgs.each {
                argumentFetcher.findValue(it, arguments)
            }

            return true

        } catch (MissingFormatArgumentException ex) {
            return false
        }

    }
}
