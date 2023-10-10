package ru.epserv.deltabrix.paperutils.profile

import com.destroystokyo.paper.profile.PlayerProfile
import com.destroystokyo.paper.profile.ProfileProperty
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import ru.epserv.deltabrix.paperutils.PaperUtils
import java.util.*
import kotlin.NoSuchElementException

class ProfileUtil {
    companion object {
        fun skin(target: Player, nickname: String) {
            Bukkit.createProfile(nickname).update().thenAccept {
                Bukkit.getScheduler().runTask(PaperUtils.instance(), Runnable {
                    val newProfile = Bukkit.createProfileExact(target.uniqueId, target.name)
                    newProfile.setProperties(it.properties)
                    target.playerProfile = newProfile
                })
            }
        }

        fun name(target: Player, nickname: String) {
            Bukkit.createProfile(nickname).update().thenAccept {
                Bukkit.getScheduler().runTask(PaperUtils.instance(), Runnable {
                    val newProfile = Bukkit.createProfileExact(target.uniqueId, nickname)
                    newProfile.setProperties(newProfile.properties.apply {
                        it.properties.add(ProfileProperty("originalUUID", target.uniqueId.toString()))
                        it.properties.add(ProfileProperty("originalName", target.name))
                    })
                    target.playerProfile = newProfile
                })
            }
        }

        fun reset(target: Player) {
            Bukkit.getScheduler().runTask(PaperUtils.instance(), Runnable {
                val oldProfile: Pair<ProfileProperty, ProfileProperty>? = getOriginalPropertiesValues(target.playerProfile)
                val newProfile = Bukkit.createProfileExact(UUID.fromString(oldProfile!!.first.value), oldProfile.second.value)
                target.playerProfile = newProfile
            })
        }

        fun getOriginalPropertiesValues(profile: PlayerProfile): Pair<ProfileProperty, ProfileProperty>? {
            return try {
                val originalUUID: ProfileProperty = profile.properties.first { it.name == "originalUUID" }
                val originalName: ProfileProperty = profile.properties.first { it.name == "originalName" }
                Pair(originalName, originalUUID)
            } catch (e: NoSuchElementException) {
                null
            }
        }
    }
}

