package ua.com.dkazhika.superheroes.data.cache

import ua.com.dkazhika.superheroes.data.HeroData
import ua.com.dkazhika.superheroes.data.HeroDataToDbMapper

interface HeroesCacheDataSource {

    fun fetchHeroes(): List<HeroDb>

    fun saveHeroes(heroes: List<HeroData>)

    class Base(private val realmProvider: RealmProvider, private val mapper: HeroDataToDbMapper) : HeroesCacheDataSource {

        override fun fetchHeroes(): List<HeroDb> {
            realmProvider.provide().use { realm ->
                val results = realm.where(HeroDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(results)
            }
        }

        override fun saveHeroes(heroes: List<HeroData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    heroes.forEach { hero ->
                        hero.mapTo(mapper, it)
                    }
                }
            }
        }
    }
}