package ua.com.dkazhika.superheroes.data.cache

import ua.com.dkazhika.superheroes.core.Hero

interface HeroesCacheDataSource {

    fun fetchHeroes(): List<HeroDb>

    fun saveHeroes(heroes: List<Hero>)

    class Base(private val realmProvider: RealmProvider) : HeroesCacheDataSource {

        override fun fetchHeroes(): List<HeroDb> {
            realmProvider.provide().use { realm ->
                val results = realm.where(HeroDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(results)
            }
        }

        override fun saveHeroes(heroes: List<Hero>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    heroes.forEach { hero ->
                        it.createObject(HeroDb::class.java, hero.id).apply {
                            name = hero.name
                            description = hero.description
                            imageUrl = hero.imageUrl
                        }
                    }
                }
            }
        }
    }
}