package com.lyfebloc.erc20kit.core

import com.lyfebloc.ethereumkit.core.IEip20Storage
import com.lyfebloc.ethereumkit.core.ITransactionProvider
import com.lyfebloc.ethereumkit.core.ITransactionSyncer
import com.lyfebloc.ethereumkit.models.Eip20Event
import com.lyfebloc.ethereumkit.models.ProviderTokenTransaction
import com.lyfebloc.ethereumkit.models.Transaction
import io.reactivex.Single

class Erc20TransactionSyncer(
        private val transactionProvider: ITransactionProvider,
        private val storage: IEip20Storage
) : ITransactionSyncer {

    private fun handle(transactions: List<ProviderTokenTransaction>) {
        if (transactions.isEmpty()) return

        val events = transactions.map { tx ->
            Eip20Event(tx.hash, tx.blockNumber, tx.contractAddress, tx.from, tx.to, tx.value, tx.tokenName, tx.tokenSymbol, tx.tokenDecimal)
        }

        storage.save(events)
    }

    override fun getTransactionsSingle(): Single<Pair<List<Transaction>, Boolean>> {
        val lastTransactionBlockNumber = storage.getLastEvent()?.blockNumber ?: 0
        val initial: Boolean = lastTransactionBlockNumber == 0L

        return transactionProvider.getTokenTransactions(lastTransactionBlockNumber + 1)
                .doOnSuccess { providerTokenTransactions -> handle(providerTokenTransactions) }
                .map { providerTokenTransactions ->
                    val array = providerTokenTransactions.map { transaction ->
                        Transaction(
                                hash = transaction.hash,
                                timestamp = transaction.timestamp,
                                isFailed = false,
                                blockNumber = transaction.blockNumber,
                                transactionIndex = transaction.transactionIndex,
                                nonce = transaction.nonce,
                                gasPrice = transaction.gasPrice,
                                gasLimit = transaction.gasLimit,
                                gasUsed = transaction.gasUsed
                        )

                    }
                    Pair(array, initial)
                }
                .onErrorReturnItem(Pair(listOf(), initial))
    }

}
