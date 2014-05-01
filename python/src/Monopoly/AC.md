When I start game of 3 players
Then I see 32 houses on board
And 12 hotels
And community chest pile
And chance pile
And 3 players

Given game is not started
When I roll dice
Then the highest roll become first player

When playerA end his turn
Then next playerB become current player

Given game has started
When playerA roll dice 5
Then playerA move by 5

Given game has started and player A stands on spot 38
When playerA roll dice of 6
Then playerA get to spot 4


When playerA pass by starting point
Then playerA get $200

Given playerA has $300
When playerA withdraw $200 from bank
Then playerA has $500

Given playerA has $300
When playerA pay $200 to bank
Then playerA has $100



Given propertyX has no owner
When playerA get to propertyX
Then playerA can choose to buy it

Given propertyX has no owner
When playerA choose to buy it
Then playerA pay the price
And playerA become the owner



Given playerA never bankrupted
When playerA is out of money
Then he should choose a property to make a settlement with bank

Given playerA has bankrpted
And playerC is the richest of the remaining
When playerA is out of money
Then playerC become winner

Given playerA has propertyX of unimproved
When he motage it to bank
Then he get half of the price



When playerA get to spot "go to jail"
Then he move to spot "jail"

Given playerA is in jail
When playerA roll a dice of double
Then he move by the roll

Given playerA is in jail
When playerA pay $50
Then he get out of jail

Given playerA is in jail
When playerA roll a dice of single
Then he stay in the jail

Given playerA has tried twice in the jail
When he roll a single again
Then he must pay $50 to get out of jail
And he move by the roll





