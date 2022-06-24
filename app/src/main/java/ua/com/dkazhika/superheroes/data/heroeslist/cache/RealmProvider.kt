package ua.com.dkazhika.superheroes.data.heroeslist.cache

import io.realm.Realm
import io.realm.RealmConfiguration

interface RealmProvider {

    fun provide(): Realm

    class Base() : RealmProvider {
        override fun provide(): Realm {
            val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
            return Realm.getInstance(config)
        }
    }
}