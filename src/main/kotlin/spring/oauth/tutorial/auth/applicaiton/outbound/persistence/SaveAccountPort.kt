package spring.oauth.tutorial.auth.applicaiton.outbound.persistence

import spring.oauth.tutorial.auth.domain.Account

interface SaveAccountPort {

    fun save(account: Account): Account
}
