# BlackJack
MCT File Submission
# CONTENTS:

-
## Introduction
-
## Installation
-
## Working
-
## Features
-
## Acknowledgements

# Introduction:

## Blackjack is a very easy to understand single player card based game. The game has a dealer(computer) and a player(user). The game starts off with asking you to place a bet. All players start with $1000. Once a bet is placed, the dealer matches the bet. So if the player wins the game, he essentially doubles his money.

## Once the bet is placed, the bet textview changes to the pot. The dealer deals himself one card, and deals the player another card. Once the player has his card, he has two options- hit(deal another card) or stand(don&#39;t deal). The ultimate goal for the player is to get a total of 21, if not get as close to 21 as possible without going above 21. If the player hits 21, he automatically wins. If he goes beyond 21, the player loses. If the player chooses to stand before he hits 21, the dealer starts dealing cards to himself. If the dealer gets a sum higher than the player and less than or equal to 21, the player loses. Once the game is done, the player can continue playing with the remaining money he has left.

# Installation:

## The game can be installed by using the apk that is generated by android studio.

## To install an APK, unknown source installation must be tuned on. To do so, follow the instructions below:

1.
## Launch settings on your android device
2.
## \*Tap on security (or privacy)

![](RackMultipart20210424-4-jcd1he_html_41456f187ca7d6e6.png)

1.
## Under &quot;App Installation&quot; click on unknown source installation

![](RackMultipart20210424-4-jcd1he_html_1183ad2ef1281fa7.png)

1.
## Select your preferred browser/app you are using to

## download the apk(google chrome for example)

![](RackMultipart20210424-4-jcd1he_html_dc259ba9b00f9aa1.png)

## \*You can instead search for &quot;Unknown Sources&quot; and skip step 3.

# WORKING:

-
## XML: The &quot;parent layout&quot; is a relative layout, which makes arranging all the buttons/textviews easy. The relative layout holds:

-
## TextView: Holds Total Bank Amount
-
## HorizontalScrollView:



## LinearLayout: Holds all card images when cards are drawn by the dealer

-
## LinearLayout:



## TextView: Holds Bet/Pot amount



## TextView: Holds Dealer Sum



## TextView: Holds Player Sum



## Button: Increase Bet Amount



## Button: Decrease Bet Amount

-
## HorizontalScrollView:



## LinearLayout: Holds all card images when drawn by the player

-
## LinearLayout:



## Button: Hit Button/Start Game Button



## Button: Stand Button



## Button: Restart Game Button



## TextView: Games Won/Total

-
## KOTLIN: A custom Card class is defined, this class has 3 data items: value, face and picture. All card pictures are stored in R.Drawable. An array is created holding 52 card objects. This array is then copied into a mutableList. The inbuilt shuffle function is called, now we have a shuffled deck of cards. When a card is drawn, the first card in the mutable list is taken, its image file is added to the horizontalScrollView and its value is added to the player/dealer sum.

## Once a card is drawn, a function checks if the &quot;state of the game&quot;, that is, is the game won/lost or is there still another turn.

# Working:

## Here is a gif showing the game off:
<img src="https://user-images.githubusercontent.com/82951104/115983293-f4d60200-a5bd-11eb-918a-5b3c0b2072d5.png" width="90%"></img>

![](RackMultipart20210424-4-jcd1he_html_b198f5accd73bdbf.gif)

## Bonus gif:

![](RackMultipart20210424-4-jcd1he_html_8348e08bc5ebb9fe.gif)

# FEATURES:

-
## The app is dark themed all around, starting from the logo of the app itself:

![](RackMultipart20210424-4-jcd1he_html_4b659c58fed68a4d.png)

-
## The app itself has a dark and beautiful background with yellow text, blue buttons and white cards, making the content pop out more and making it easy to read.
-
## The plus and minus buttons have 2 functionalities. A tap, changes bet amount by 10, and a hold changes bet amount by 100. This makes placing larger bets easy while also being able to make small changes by 10 possible.
-
## The app also has audio feedback. When you start a new game, you hear a very satisfying card shuffle sound. An equally satisfying &quot;card draw&quot; sound is also heard, when the player chooses to &quot;hit&quot;. This keeps the user immersed in the game, and they ultimately enjoy it more.
-
## Speaking of immersion, the app always runs in fullscreen mode, hiding the titlebar and the navigation bar. This gives the app a really nice fullscreen effect. The nav bar can be brought back up by swiping on the bottom of the screen. The app runs a 3 second timer and then proceeds to hide the nav bar again to keep the user immersed and to also give more space for all the contents of the app
-
## Blackjack is an app that is best experienced in portrait mode. So the app is designed to not turn into landscape mode, even if the phone is flipped.
-
## Since number of cards drawn in each game is different, the game is smart enough to automatically scroll right to the latest card drawn. This enables the user to see immediately what card they are going to draw.
-
## After giving the app for playtesting to a few friends and family members, the number one requested feature was a way to keep track of number of games won. This opens up a possibility of players battling each other out by playing a set number(say 5) games and see who wins the most or who earns the most money. A very subtle textview which shows total games won over total games played was added in a later version of the app, and it was a huge success.
-
## No matter how addicting the game is, once the bank balance goes below $50(minimum bet), there is no more playing. The app then gives a really nice &quot;GG (Good Game)&quot; toast, and automatically closes the app, making the experience come full circle.

# ACKNOWLEDGEMENTS:

-
## A bunch of Stack Overflow threads were very useful and helped me discover a lot of features android studio has to offer
-
## Google&#39;s official android developer website gave me a function which makes the app fullscreen.
-
## I would also like to thank all my friends who helped me play test the app and report bugs, and give more ideas, making the app better and more enjoyable by the day.
