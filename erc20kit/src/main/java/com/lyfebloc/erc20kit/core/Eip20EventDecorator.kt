package com.lyfebloc.erc20kit.core

import com.lyfebloc.erc20kit.events.ApproveEventInstance
import com.lyfebloc.erc20kit.events.TokenInfo
import com.lyfebloc.erc20kit.events.TransferEventInstance
import com.lyfebloc.ethereumkit.contracts.ContractEventInstance
import com.lyfebloc.ethereumkit.core.IEip20Storage
import com.lyfebloc.ethereumkit.core.IEventDecorator
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.Transaction
import com.lyfebloc.ethereumkit.models.TransactionLog

class Eip20EventDecorator(
    private val userAddress: Address,
    private val storage: IEip20Storage
) : IEventDecorator {

    override fun contractEventInstancesMap(transactions: List<Transaction>): Map<String, List<ContractEventInstance>> {
        val erc20Events = if (transactions.size > 100) {
            storage.getEvents()
        } else {
            storage.getEventsByHashes(transactions.map { it.hash })
        }

        val map: MutableMap<String, List<ContractEventInstance>> = mutableMapOf()

        for (event in erc20Events) {
            val tokenInfo = TokenInfo(event.tokenName, event.tokenSymbol, event.tokenDecimal)
            val eventInstance = TransferEventInstance(event.contractAddress, event.from, event.to, event.value, tokenInfo)

            map[event.hashString] = (map[event.hashString] ?: listOf()) + listOf(eventInstance)
        }

        return map
    }

    override fun contractEventInstances(logs: List<TransactionLog>): List<ContractEventInstance> {
        return logs.mapNotNull { log ->
            val event = log.getErc20EventInstance() ?: return@mapNotNull null

            when (event) {
                is TransferEventInstance -> {
                    if (event.from == userAddress || event.to == userAddress) {
                        return@mapNotNull event
                    }
                }
                is ApproveEventInstance -> {
                    if (event.owner == userAddress || event.spender == userAddress) {
                        return@mapNotNull event
                    }
                }
            }

            return@mapNotNull null
        }
    }

}
