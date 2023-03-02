package com.lyfebloc.nftkit.core

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.contracts.ContractMethodFactories
import com.lyfebloc.ethereumkit.core.IMethodDecorator

class Eip1155MethodDecorator(
    private val contractMethodFactories: ContractMethodFactories
) : IMethodDecorator {
    override fun contractMethod(input: ByteArray): ContractMethod? {
        return contractMethodFactories.createMethodFromInput(input)
    }
}
