package com.lyfebloc.oneinchkit.contracts

import com.lyfebloc.ethereumkit.contracts.ContractMethodFactories
import com.lyfebloc.oneinchkit.contracts.v4.SwapMethodFactoryV4
import com.lyfebloc.oneinchkit.contracts.v4.UnoswapMethodFactoryV4
import com.lyfebloc.oneinchkit.contracts.v4.UnparsedSwapMethodsFactoryV4
import com.lyfebloc.oneinchkit.contracts.v5.SwapMethodFactoryV5
import com.lyfebloc.oneinchkit.contracts.v5.UnoswapMethodFactoryV5
import com.lyfebloc.oneinchkit.contracts.v5.UnparsedSwapMethodsFactoryV5

object OneInchContractMethodFactories : ContractMethodFactories() {

    init {
        val swapContractMethodFactories = listOf(
            UnoswapMethodFactoryV5(),
            SwapMethodFactoryV5(),
            UnparsedSwapMethodsFactoryV5(),
            UnoswapMethodFactoryV4(),
            SwapMethodFactoryV4(),
            UnparsedSwapMethodsFactoryV4()
        )
        registerMethodFactories(swapContractMethodFactories)
    }

}
