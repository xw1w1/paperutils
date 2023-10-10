package ru.epserv.deltabrix.paperutils.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.epserv.deltabrix.paperutils.profile.ProfileUtil

class NicknameCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val input: String = args!![0]
            ProfileUtil.name(sender, input)
        }
        return false
    }

}