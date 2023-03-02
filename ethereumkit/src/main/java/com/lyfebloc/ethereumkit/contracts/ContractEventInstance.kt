package com.lyfebloc.ethereumkit.contracts

import com.lyfebloc.ethereumkit.models.Address

open class ContractEventInstance(val contractAddress: Address) {

    open fun tags(userAddress: Address): List<String> = listOf()

}
