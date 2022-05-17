package ua.com.dkazhika.superheroes.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero

open class HeroDb : RealmObject(), Abstract.Mapable<Hero, HeroCacheMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    var imageUrl: String = ""

    override fun map(mapper: HeroCacheMapper): Hero {
        return mapper.map(id, name, description, imageUrl)
    }
}
