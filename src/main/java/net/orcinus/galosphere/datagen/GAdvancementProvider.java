package net.orcinus.galosphere.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.SummonedEntityTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.criterion.GCriterion;
import net.orcinus.galosphere.init.GBlocks;
import net.orcinus.galosphere.init.GCriteriaTriggers;
import net.orcinus.galosphere.init.GEntityTypes;
import net.orcinus.galosphere.init.GItems;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GAdvancementProvider extends ForgeAdvancementProvider {

    public GAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new GAdvancementGenerator()));
    }

    public static class GAdvancementGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            this.generateAdventures(consumer, provider);
            this.generateHusbandry(consumer);
        }

        private void generateHusbandry(Consumer<AdvancementHolder> consumer) {
            Advancement.Builder.advancement()
                    .parent(ResourceLocation.withDefaultNamespace("husbandry/plant_seed"))
                    .display(
                            GItems.LUMIERE_SHARD.get(),
                            Component.translatable("advancements.galosphere.lumiere_compost.title"),
                            Component.translatable("advancements.galosphere.lumiere_compost.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "lumiere_compost",
                            GCriteriaTriggers.LUMIERE_COMPOST.get().createCriterion(new GCriterion.TriggerInstance(Optional.empty()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("husbandry/lumiere_compost"));
        }

        private void generateAdventures(Consumer<AdvancementHolder> consumer, HolderLookup.Provider provider) {
            Advancement.Builder.advancement()
                    .parent(Galosphere.id("adventure/find_pink_salt_shrine"))
                    .display(
                            GBlocks.PINK_SALT_CHAMBER.get(),
                            Component.translatable("advancements.galosphere.activate_pink_salt_chamber.title"),
                            Component.translatable("advancements.galosphere.activate_pink_salt_chamber.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "activate_pink_salt_chamber",
                            GCriteriaTriggers.ACTIVATE_PINK_SALT_CHAMBER.get().createCriterion(new GCriterion.TriggerInstance(Optional.empty()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/activate_pink_salt_chamber"));

            Advancement.Builder.advancement()
                    .parent(ResourceLocation.withDefaultNamespace("adventure/root"))
                    .display(
                            GBlocks.AMETHYST_LAMP.get(),
                            Component.translatable("advancements.galosphere.crystal_lamps.title"),
                            Component.translatable("advancements.galosphere.crystal_lamps.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "crystal_lamps",
                            InventoryChangeTrigger.TriggerInstance.hasItems(GBlocks.ALLURITE_LAMP.get(), GBlocks.LUMIERE_LAMP.get(), GBlocks.AMETHYST_LAMP.get())
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/crystal_lamps"));

//            Advancement.Builder.advancement()
//                    .parent(ResourceLocation.withDefaultNamespace("adventure/root"))
//                    .display(
//                            GBlocks.PINK_SALT_BRICKS.get(),
//                            Component.translatable("advancements.galosphere.find_pink_salt_shrine.title"),
//                            Component.translatable("advancements.galosphere.find_pink_salt_shrine.description"),
//                            null,
//                            AdvancementType.TASK,
//                            true,
//                            true,
//                            false
//                    )
//                    .addCriterion(
//                            "pink_salt_shrine",
//                            PlayerTrigger.TriggerInstance.located(
//                                    LocationPredicate.Builder.inStructure(provider.lookupOrThrow(Registries.STRUCTURE).getOrThrow(GStructures.PINK_SALT_SHRINE))
//                            )
//                    )
//                    .requirements(AdvancementRequirements.Strategy.AND)
//                    .save(consumer, Galosphere.id("adventure/find_pink_salt_shrine"));

            Advancement.Builder.advancement()
                    .parent(ResourceLocation.withDefaultNamespace("adventure/root"))
                    .display(
                            GItems.GLOW_FLARE.get(),
                            Component.translatable("advancements.galosphere.light_spread.title"),
                            Component.translatable("advancements.galosphere.light_spread.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "light_spread",
                            GCriteriaTriggers.LIGHT_SPREAD.get().createCriterion(new GCriterion.TriggerInstance(Optional.empty()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/light_spread"));

            Advancement.Builder.advancement()
                    .parent(Galosphere.id("adventure/silver_ingot"))
                    .display(
                            GItems.SILVER_BOMB.get(),
                            Component.translatable("advancements.galosphere.silver_bomb.title"),
                            Component.translatable("advancements.galosphere.silver_bomb.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "silver_bomb",
                            InventoryChangeTrigger.TriggerInstance.hasItems(GItems.SILVER_BOMB.get())
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/silver_bomb"));

            Advancement.Builder.advancement()
                    .parent(Galosphere.id("adventure/silver_ingot"))
                    .display(
                            GItems.STERLING_HELMET.get(),
                            Component.translatable("advancements.galosphere.sterling_armor.title"),
                            Component.translatable("advancements.galosphere.sterling_armor.description"),
                            null,
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "sterling_armor",
                            InventoryChangeTrigger.TriggerInstance.hasItems(
                                    GItems.STERLING_HELMET.get(),
                                    GItems.STERLING_CHESTPLATE.get(),
                                    GItems.STERLING_LEGGINGS.get(),
                                    GItems.STERLING_BOOTS.get()
                            )
                    )
                    .rewards(AdvancementRewards.Builder.experience(100))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/sterling_armor"));

            Advancement.Builder.advancement()
                    .parent(ResourceLocation.withDefaultNamespace("adventure/root"))
                    .display(
                            GItems.PRESERVED_FLESH.get(),
                            Component.translatable("advancements.galosphere.summon_berserker.title"),
                            Component.translatable("advancements.galosphere.summon_berserker.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "summoned",
                            SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(GEntityTypes.BERSERKER.get()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/summon_berserker"));

            Advancement.Builder.advancement()
                    .parent(ResourceLocation.withDefaultNamespace("adventure/root"))
                    .display(
                            GItems.SPECTRE_FLARE.get(),
                            Component.translatable("advancements.galosphere.use_spectre_flare.title"),
                            Component.translatable("advancements.galosphere.use_spectre_flare.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "use_spectre_flare",
                            GCriteriaTriggers.USE_SPECTRE_FLARE.get().createCriterion(new GCriterion.TriggerInstance(Optional.empty()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/use_spectre_flare"));

            Advancement.Builder.advancement()
                    .parent(ResourceLocation.withDefaultNamespace("adventure/root"))
                    .display(
                            GItems.SPECTRE_BOUND_SPYGLASS.get(),
                            Component.translatable("advancements.galosphere.use_spectre_spyglass.title"),
                            Component.translatable("advancements.galosphere.use_spectre_spyglass.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "use_spectre_spyglass",
                            GCriteriaTriggers.USE_SPECTRE_SPYGLASS.get().createCriterion(new GCriterion.TriggerInstance(Optional.empty()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/use_spectre_spyglass"));

            Advancement.Builder.advancement()
                    .parent(Galosphere.id("adventure/silver_ingot"))
                    .display(
                            Items.ENDER_PEARL,
                            Component.translatable("advancements.galosphere.warped_teleport.title"),
                            Component.translatable("advancements.galosphere.warped_teleport.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion(
                            "warped_teleport",
                            GCriteriaTriggers.WARPED_TELEPORT.get().createCriterion(new GCriterion.TriggerInstance(Optional.empty()))
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, Galosphere.id("adventure/warped_teleport"));
        }

    }

}
