package ua.com.dkazhika.superheroes.data.heroeslist.cache

import io.realm.Realm

interface DbWrapper {

    fun createObject(id: Int) : HeroDb

    class Base(private val realm: Realm) : DbWrapper {
        override fun createObject(id: Int): HeroDb {
            return realm.createObject(HeroDb::class.java, id)
        }
    }
}