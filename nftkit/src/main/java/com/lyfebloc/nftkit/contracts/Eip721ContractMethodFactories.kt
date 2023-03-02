package com.lyfebloc.nftkit.contracts

import com.lyfebloc.ethereumkit.contracts.ContractMethodFactories

object Eip721ContractMethodFactories : ContractMethodFactories() {
    init {
        registerMethodFactories(listOf(Eip721SafeTransferFromMethodFactory()))
    }
}
