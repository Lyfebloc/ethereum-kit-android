package com.lyfebloc.uniswapkit.contract

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.contracts.ContractMethodFactory
import com.lyfebloc.ethereumkit.contracts.ContractMethodHelper
import com.lyfebloc.ethereumkit.models.Address
import java.math.BigInteger

object SwapExactETHForTokensMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(SwapExactETHForTokensMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, listOf(BigInteger::class, List::class, Address::class, BigInteger::class))
        val amountOutMin = parsedArguments[0] as BigInteger
        val path = parsedArguments[1] as List<Address>
        val to = parsedArguments[2] as Address
        val deadline = parsedArguments[3] as BigInteger
        return SwapExactETHForTokensMethod(amountOutMin, path, to, deadline)
    }

}
