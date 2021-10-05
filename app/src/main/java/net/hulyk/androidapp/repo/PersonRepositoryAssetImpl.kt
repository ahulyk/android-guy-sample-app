package net.hulyk.androidapp.repo

import net.hulyk.androidapp.asset.AssetProvider
import net.hulyk.androidapp.dto.PersonData
import net.hulyk.androidapp.json.Parser
import net.hulyk.androidapp.json.parseList
import javax.inject.Inject


class PersonRepositoryAssetImpl @Inject constructor(
    private val parser: Parser,
    private val assetProvider: AssetProvider
) : PersonRepository {
    override fun getPersons(): List<PersonData> {
        return parser.parseList(assetProvider.openFileStream("android_guys.json"))
    }

}