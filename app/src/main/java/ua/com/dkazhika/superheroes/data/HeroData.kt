package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.cache.DbWrapper
import ua.com.dkazhika.superheroes.data.cache.HeroDb
import ua.com.dkazhika.superheroes.domain.HeroDomain

data class HeroData(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val imageUrl: String
) : Abstract.Object<HeroDomain, HeroDataToDomainMapper>, ToHeroDb<HeroDb, HeroDataToDbMapper> {
    override fun map(mapper: HeroDataToDomainMapper): HeroDomain {
        return mapper.map(id, name, description, imageUrl)
    }

    override fun mapTo(mapper: HeroDataToDbMapper, db: DbWrapper): HeroDb {
        return mapper.mapToDb(id, name, description, imageUrl, db)
    }
}

//todo make better later
interface ToHeroDb<T, M : Abstract.Mapper> {
    fun mapTo(mapper: HeroDataToDbMapper, db: DbWrapper): T
}