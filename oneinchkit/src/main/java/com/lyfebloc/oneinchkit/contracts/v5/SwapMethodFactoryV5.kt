package com.lyfebloc.oneinchkit.contracts.v5

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.contracts.ContractMethodFactory
import com.lyfebloc.ethereumkit.contracts.ContractMethodHelper
import com.lyfebloc.ethereumkit.models.Address
import java.math.BigInteger

class SwapMethodFactoryV5 : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(SwapMethodV5.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val argumentTypes = listOf(
            Address::class,
            ContractMethodHelper.StaticStruct(
                listOf(
                    Address::class,
                    Address::class,
                    Address::class,
                    Address::class,
                    BigInteger::class,
                    BigInteger::class,
                    BigInteger::class
                )
            ),
            ByteArray::class,
            ByteArray::class
        )
        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, argumentTypes)

        val caller = parsedArguments[0] as Address
        val swapDescriptionArguments = parsedArguments[1] as List<*>

        val srcToken = swapDescriptionArguments[0] as Address
        val dstToken = swapDescriptionArguments[1] as Address
        val srcReceiver = swapDescriptionArguments[2] as Address
        val dstReceiver = swapDescriptionArguments[3] as Address
        val amount = swapDescriptionArguments[4] as BigInteger
        val minReturnAmount = swapDescriptionArguments[5] as BigInteger
        val flags = swapDescriptionArguments[6] as BigInteger

        val swapDescription = SwapMethodV5.SwapDescription(srcToken, dstToken, srcReceiver, dstReceiver, amount, minReturnAmount, flags)

        val permit = parsedArguments[2] as ByteArray
        val data = parsedArguments[3] as ByteArray

        return SwapMethodV5(caller, swapDescription, permit, data)
    }

}
