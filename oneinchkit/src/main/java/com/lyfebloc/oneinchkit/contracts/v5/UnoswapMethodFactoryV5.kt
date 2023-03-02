package com.lyfebloc.oneinchkit.contracts.v5

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.contracts.ContractMethodFactory
import com.lyfebloc.ethereumkit.contracts.ContractMethodHelper
import com.lyfebloc.ethereumkit.models.Address
import java.math.BigInteger

class UnoswapMethodFactoryV5 : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(UnoswapMethodV5.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val argumentTypes = listOf(Address::class, BigInteger::class, BigInteger::class, List::class)

        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, argumentTypes)

        val srcToken = parsedArguments[0] as Address
        val amount = parsedArguments[1] as BigInteger
        val minReturn = parsedArguments[2] as BigInteger
        val params = parsedArguments[3] as List<BigInteger>

        return UnoswapMethodV5(srcToken, amount, minReturn, params)
    }

}
