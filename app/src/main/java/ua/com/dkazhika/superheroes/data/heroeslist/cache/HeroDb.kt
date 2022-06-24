package ua.com.dkazhika.superheroes.data.heroeslist.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData

open class HeroDb : RealmObject(), Abstract.Object<HeroData, HeroCacheMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var imageUrl: String = ""

    override fun map(mapper: HeroCacheMapper): HeroData {
        return mapper.map(id, name, imageUrl)
    }
}
