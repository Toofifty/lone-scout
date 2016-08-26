Lone Scout
==========

Repository for Lone Scout, a side-scrolling procedural platformer written in Java using Slick2D.

# Proposed Gameplay

## Story

You are a lone scout, faced with the task of finding an inhabitable planet that is safe for a civilisation.

Once you land on a new planet, your ship will not have enough fuel to travel to another. You will need to fight off
the native fauna in order to gain enough fuel to scout another world. Earlier explorers have left supply packages on
the surface of the planets that you can use to upgrade your suit and weapons. The more habitable a planet is for you,
the more habitable it is to the dangerous creatures that live there.

## Levels

Your character's level will affect the gun stats and perks you can receive. Experience is gained from killing enemies,
finding supply packages and discovering new planets. Your levels do not affect your damage output or defense.

## Supply packages

Littered on the surfaces of planets, they could be one of the following:
* Weapon crate
* Suit upgrade package
* (Temporary) buff package

There is two types of package: common and advanced. Common packages are more prevalent, and can be opened at no cost.
They will usually only contain ammunition but may contain perks or buffs. Weapons cannot be found in common packages.
Advanced packages may incur a penalty, either a temporary de-buff, blood sacrifice, perk sacrifice or task to complete
in order to be opened. They contain better rewards.

## Weapons

On being dropped (from a supply package or a creature), a weapon's stats will be generated based on your character's
level. There are several types of guns, all with varying fire rates, magazine sizes and damage throughput. You will be
able to hold two guns at any one time, with the ability to switch between them and pick up new guns that have been
dropped.

## Perks

Perks are also dropped from both supply packages and creatures. These affect both your suit and weapon. A higher
character level will give a higher chance at better perks being dropped, but the best perks will only be dropped from
advanced packages.

There are 3 types of perks:
* Multiplier - a stack-able percentage increase to movement speed, jump height, shield, damage, fire rate or magazine
size
* Upgrade - a stack-able single upgrade to your suit. e.g. An extra jump, follower drone, extra health regen
* Single - an added ability that does not stack. e.g. negated fall damage, enemies explode when killed

Each perk has a rarity value that determines when it can be dropped. Character level is factored into this value.

## Items

Potential feature.

Items will be single use, and very powerful. e.g. meteor strike, second wind (revive on death), targeted strike
Also includes the jump cell, an item that is used to travel to a new planet. Jump cells will be a 100% drop rate on
mega-fauna (bosses), and killing these bosses is the main method of progressing in the game.

## Fauna

Enemies or mobs. When killed, they may drop ammo or experience. Each may spawn only once per planet (no respawns) but
can be summoned by mega-fauna during a battle. Their health and damage will scale to the habitability of the planet.

## Mega-fauna (bosses)

Summoned by reaching and activating a structure. High health, and each will have different abilities. Multiple summoning
structures may appear on rare occasions. The first boss killed on any planet will drop a jump cell, with a low chance
of dropping a rare weapon, perk or item. Further bosses will have an equal chance to drop a rare or a jump cell.

## Planets

Generated procedurally using a seed. Lush planets will have flora and native fauna, barren planets are more likely to
have robotic races.