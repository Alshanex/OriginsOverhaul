######## Removes Diamond ########

clear @s minecraft:diamond 1
scoreboard players set @s diamond_timer 3600
scoreboard players set @s forgetime 0
effect clear @s jump_boost

######## 3rd Tier Upgrade ########

attribute @s[tag=diamondbuff2] generic.movement_speed modifier add eedcdc42-5646-4cfe-9158-2730e0a07662 "diamondmovebuff3" 0.01 add
attribute @s[tag=diamondbuff2] generic.attack_speed modifier add 9aea219b-9ac9-47ec-98aa-d63d9e8c1590 "diamondattackbuff3" 0.05 multiply_base
tag @s[tag=diamondbuff2] add diamondbuff3

######## 2nd Tier Upgrade ########

attribute @s[tag=diamondbuff1] generic.movement_speed modifier add 7abeb73d-1b00-4c29-a471-4a69bee07c66 "diamondmovebuff2" 0.01 add
attribute @s[tag=diamondbuff1] generic.attack_speed modifier add 9dff8e25-84a2-4d23-86b5-7e8bba5dd371 "diamondattackbuff2" 0.05 multiply_base
tag @s[tag=diamondbuff1] add diamondbuff2

######## 1st Tier Upgrade ########

attribute @s generic.movement_speed modifier add aa68f78b-2e81-450b-86bc-c62f87eb32a0 "diamondmovebuff1" 0.01 add
attribute @s generic.attack_speed modifier add 23588f68-acfa-41ba-8e85-46ae55c5b5ea "diamondattackbuff1" 0.05 multiply_base
tag @s add diamondbuff1