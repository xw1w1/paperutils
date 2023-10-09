package ru.epserv.deltabrix.paperutils

import org.bukkit.plugin.java.JavaPlugin
import ru.epserv.deltabrix.paperutils.commands.NicknameCommand
import ru.epserv.deltabrix.paperutils.commands.ProfileResetCommand
import ru.epserv.deltabrix.paperutils.commands.SkinCommand

class PaperUtils : JavaPlugin() {

    override fun onEnable() {
        getCommand("profilereset")?.setExecutor(ProfileResetCommand())
        getCommand("nickname")?.setExecutor(NicknameCommand())
        getCommand("skin")?.setExecutor(SkinCommand())
    }

    companion object {
        fun instance(): PaperUtils {
            return getPlugin(PaperUtils::class.java)
        }
    }

}