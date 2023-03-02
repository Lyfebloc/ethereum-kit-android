package com.lyfebloc.erc20kit.contract

import com.lyfebloc.ethereumkit.contracts.ContractMethodFactory
import com.lyfebloc.ethereumkit.contracts.ContractMethodHelper
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.spv.core.toBigInteger

object ApproveMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(ApproveMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ApproveMethod {
        val address = Address(inputArguments.copyOfRange(12, 32))
        val value = inputArguments.copyOfRange(32, 64).toBigInteger()

        return ApproveMethod(address, value)
    }

}
