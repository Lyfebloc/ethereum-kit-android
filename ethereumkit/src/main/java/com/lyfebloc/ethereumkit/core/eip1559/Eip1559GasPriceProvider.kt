package com.lyfebloc.ethereumkit.core.eip1559

import com.lyfebloc.ethereumkit.core.EthereumKit
import com.lyfebloc.ethereumkit.models.DefaultBlockParameter
import io.reactivex.Flowable
import io.reactivex.Single

class Eip1559GasPriceProvider(
        private val evmKit: EthereumKit
) {
    fun feeHistory(
            blocksCount: Long,
            rewardPercentile: List<Int>,
            defaultBlockParameter: DefaultBlockParameter = DefaultBlockParameter.Latest
    ): Flowable<FeeHistory> {
        return evmKit.lastBlockHeightFlowable
                .flatMapSingle {
                    feeHistorySingle(blocksCount, defaultBlockParameter, rewardPercentile)
                }
    }

    fun feeHistorySingle(blocksCount: Long, defaultBlockParameter: DefaultBlockParameter, rewardPercentile: List<Int>): Single<FeeHistory> {
        val feeHistoryRequest = FeeHistoryJsonRpc(
                blocksCount,
                defaultBlockParameter,
                rewardPercentile
        )
        return evmKit.rpcSingle(feeHistoryRequest)
    }
}
