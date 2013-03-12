package mb.usecase

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import mb.argumentprocessing.ArgumentsRangeValidator
import mb.argumentprocessing.ArgumentsUsagePrinter
import mb.argumentprocessing.ArgumentsValueValidator
import mb.argumentprocessing.ProvidedArgumentsFetcher

@Component
class ArgumentsValidatorInteractor {

    @Autowired ArgumentsRangeValidator argumentsRangeValidator
    @Autowired ProvidedArgumentsFetcher providedArgumentsFetcher
    @Autowired ArgumentsUsagePrinter argumentsUsagePrinter
    @Autowired ArgumentsValueValidator argumentsValueValidator

    boolean isValid(Closure outputter, Map knownArgumentMap, List<String> arguments) {

        def suppliedKnownArgs = providedArgumentsFetcher.fetch(knownArgumentMap, arguments)

        if (!argumentsRangeValidator.isValid(knownArgumentMap, suppliedKnownArgs)) {
            outputter "Too few required arguments"
            return false
        }

        if (!argumentsValueValidator.isValid(suppliedKnownArgs, arguments)){
            argumentsUsagePrinter.printUsage(knownArgumentMap, outputter)
            return false
        }

        true

    }

}
