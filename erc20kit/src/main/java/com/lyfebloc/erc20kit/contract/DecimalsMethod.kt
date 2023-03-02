package com.lyfebloc.erc20kit.contract

import com.lyfebloc.ethereumkit.contracts.ContractMethod

class DecimalsMethod: ContractMethod() {
    override var methodSignature = "decimals()"
    override fun getArguments() = listOf<Any>()
}
