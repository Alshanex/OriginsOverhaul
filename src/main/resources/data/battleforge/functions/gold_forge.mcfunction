######## Removes Gold ########

clear @s minecraft:gold_ingot 1
scoreboard players set @s gold_timer 3600
effect give @s regeneration 2 2 true
scoreboard players set @s forgetime 0
effect clear @s jump_boost

######## 10th Tier Upgrade ########

attribute @s[tag=goldbuff9] generic.max_health modifier add 195f58fe-2e76-494f-b8e9-721e831e14ea "goldhealthbuff10" 2 add
tag @s[tag=goldbuff9] add goldbuff10

######## 9th Tier Upgrade ########

attribute @s[tag=goldbuff8] generic.max_health modifier add 2f078e7d-968b-44f0-979c-236fa5c478ef "goldhealthbuff9" 2 add
tag @s[tag=goldbuff8] add goldbuff9

######## 8th Tier Upgrade ########

attribute @s[tag=goldbuff7] generic.max_health modifier add 1e50eaba-1b03-4bce-aaef-a010ab8ba4a5 "goldhealthbuff8" 2 add
tag @s[tag=goldbuff7] add goldbuff8

######## 7th Tier Upgrade ########

attribute @s[tag=goldbuff6] generic.max_health modifier add 79076217-b0c8-4ae5-a8e0-82c0750356b2 "goldhealthbuff7" 2 add
tag @s[tag=goldbuff6] add goldbuff7

######## 6th Tier Upgrade ########

attribute @s[tag=goldbuff5] generic.max_health modifier add 35cf1318-926b-4360-99ec-ea32267752f7 "goldhealthbuff6" 2 add
tag @s[tag=goldbuff5] add goldbuff6

######## 5th Tier Upgrade ########

attribute @s[tag=goldbuff4] generic.max_health modifier add 852cc7a4-8963-4632-ab7f-1fce8e86003a "goldhealthbuff5" 2 add
tag @s[tag=goldbuff4] add goldbuff5

######## 4th Tier Upgrade ########

attribute @s[tag=goldbuff3] generic.max_health modifier add 3afd1a71-cff1-4fb1-b42a-1e9ccaa54cf3 "goldhealthbuff4" 2 add
tag @s[tag=goldbuff3] add goldbuff4

######## 3rd Tier Upgrade ########

attribute @s[tag=goldbuff2] generic.max_health modifier add c25b2b29-de73-4531-8243-6895047afc6e "goldhealthbuff3" 2 add
tag @s[tag=goldbuff2] add goldbuff3

######## 2nd Tier Upgrade ########

attribute @s[tag=goldbuff1] generic.max_health modifier add 1a6ed0de-81de-42e7-ae79-783fab229b89 "goldhealthbuff2" 2 add
tag @s[tag=goldbuff1] add goldbuff2

######## 1st Tier Upgrade ########

attribute @s generic.max_health modifier add c497aa95-ce66-4ec8-b03b-387d50618a9a "goldhealthbuff1" 2 add
tag @s add goldbuff1