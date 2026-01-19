package me.thecuddlybear.Hyssentials.command.home;

import com.hypixel.hytale.builtin.teleport.components.TeleportHistory;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.HeadRotation;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public class HomeCommand extends AbstractPlayerCommand {

    private final Path DataDirectory;

    public HomeCommand(Path DataDirectory) {
        super("home", "Teleport to your home.");
        this.DataDirectory = DataDirectory;
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        /*Transform homeTransform = new Transform(0,0,0);
        TransformComponent transformComponent = (TransformComponent)store.getComponent(ref, TransformComponent.getComponentType());

        assert transformComponent != null;

        HeadRotation headRotationComponent = (HeadRotation)store.getComponent(ref, HeadRotation.getComponentType());

        assert headRotationComponent != null;

        Vector3d previousPos = transformComponent.getPosition().clone();
        Vector3f previousRotation = headRotationComponent.getRotation().clone();
        TeleportHistory teleportHistoryComponent = (TeleportHistory)store.ensureAndGetComponent(ref, TeleportHistory.getComponentType());
        teleportHistoryComponent.append(world, previousPos, previousRotation, "World " + world.getName() + "'s spawn");
        Teleport teleportComponent = Teleport.createForPlayer(world, homeTransform);
        store.addComponent(ref, Teleport.getComponentType(), teleportComponent);
        Vector3d position = homeTransform.getPosition();
        commandContext.sendMessage(Message.translation("server.commands.spawn.teleported").param("x", position.getX()).param("y", position.getY()).param("z", position.getZ()));*/

        // Check if a home json file exists for the player
        UUID playerUUID = playerRef.getUuid();
        if(!this.DataDirectory.resolve("homes/" + playerUUID.toString() + ".json").toFile().exists()) {
            // Create the file
            try {
                this.DataDirectory.resolve("homes/" + playerUUID.toString() + ".json").toFile().createNewFile();
            } catch (IOException e) {
                commandContext.sendMessage(Message.raw(e.getMessage()));
            }
        }

        commandContext.sendMessage(Message.raw(this.DataDirectory.toString()));
    }
}
