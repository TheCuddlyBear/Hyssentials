package me.thecuddlybear.Hyssentials;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import me.thecuddlybear.Hyssentials.command.home.HomeCommand;

import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class HyssentialsPlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public HyssentialsPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());

        LOGGER.atInfo().log("Checking if data directory exists...");
        if (!this.getDataDirectory().toFile().exists()) {
            LOGGER.atInfo().log("Data directory does not exist, creating...");
            this.getDataDirectory().toFile().mkdirs();

            LOGGER.atInfo().log("Checking if homes directory exists...");
            this.getDataDirectory().resolve("homes").toFile().mkdirs();
            LOGGER.atInfo().log("Homes directory created.");
        }

        LOGGER.atInfo().log("Registering commands for " + this.getName());
        this.getCommandRegistry().registerCommand(new HomeCommand(this.getDataDirectory()));
    }
}