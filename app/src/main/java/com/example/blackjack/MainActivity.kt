package com.example.blackjack

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlin.concurrent.thread

import kotlin.system.exitProcess



data class Card(val face: String, val value: Int, val card: Int )

class MainActivity : AppCompatActivity() {

    private lateinit var hit:Button
    private lateinit var stand:Button
    private lateinit var restart:Button
    private lateinit var ll:LinearLayout
    private lateinit var llp:LinearLayout
    private lateinit var plus:Button
    private lateinit var minus:Button
    private lateinit var potTV:TextView
    private lateinit var playerS:TextView
    private lateinit var dealerS:TextView
    private lateinit var bankTV:TextView
    private lateinit var gameCount:TextView
    private lateinit var llpscroll: HorizontalScrollView
    private lateinit var dealerscroll: HorizontalScrollView

    private lateinit var mediaPlayerShuffle: MediaPlayer
    private lateinit var mediaPlayerDeal: MediaPlayer

    private  var turn:Int=1
    private  var playersum:Int=0
    private var dealersum:Int=0
    private var acepull:Boolean=false
    private var gameCounter:Int=0
    private var gamesWon:Int=0

    private var bank:Int=1000

    private var count:Int=-1
    private var pot:Int=50

    private val deckOriginal=listOf(
            Card("Two",2, R.drawable.clubstwo),
            Card("Two",2, R.drawable.diamondstwo),
            Card("Two",2, R.drawable.spadestwo),
            Card("Two",2, R.drawable.heartstwo),
            Card("Three",3, R.drawable.clubsthree),
            Card("Three",3, R.drawable.diamondsthree),
            Card("Three",3, R.drawable.spadesthree),
            Card("Three",3, R.drawable.heartsthree),
            Card("Four",4, R.drawable.clubsfour),
            Card("Four",4, R.drawable.diamondsfour),
            Card("Four",4, R.drawable.spadesfour),
            Card("Four",4, R.drawable.heartsfour),
            Card("Five",5, R.drawable.fiveclubs),
            Card("Five",5, R.drawable.fivediamonds),
            Card("Five",5, R.drawable.fivehearts),
            Card("Five",5, R.drawable.fivespades),
            Card("Six",6, R.drawable.sixclubs),
            Card("Six",6, R.drawable.sixdiamonds),
            Card("Six",6, R.drawable.sixhearts),
            Card("Six",6, R.drawable.sixspades),
            Card("Seven",7, R.drawable.sevenclubs),
            Card("Seven",7, R.drawable.sevendiamonds),
            Card("Seven",7, R.drawable.sevenhearts),
            Card("Seven",7, R.drawable.sevenspades),
            Card("Eight",8, R.drawable.eightclubs),
            Card("Eight",8, R.drawable.eightdiamonds),
            Card("Eight",8, R.drawable.eighthearts),
            Card("Eight",8, R.drawable.eightspades),
            Card("Nine",9, R.drawable.nineclubs),
            Card("Nine",9, R.drawable.ninediamonds),
            Card("Nine",9, R.drawable.ninehearts),
            Card("Nine",9, R.drawable.ninespades),
            Card("Ten",10, R.drawable.tenclubs),
            Card("Ten",10, R.drawable.tendiamonds),
            Card("Ten",10, R.drawable.tenhearts),
            Card("Ten",10, R.drawable.tenspades),
            Card("Jack",10, R.drawable.jc),
            Card("Jack",10, R.drawable.jd),
            Card("Jack",10, R.drawable.jh),
            Card("Jack",10, R.drawable.js),
            Card("Queen",10, R.drawable.qc),
            Card("Queen",10, R.drawable.qd),
            Card("Queen",10, R.drawable.qh),
            Card("Queen",10, R.drawable.qs),
            Card("King",10, R.drawable.kc),
            Card("King",10, R.drawable.kd),
            Card("King",10, R.drawable.kh),
            Card("King",10, R.drawable.ks),
            Card("Ace",11, R.drawable.ac),
            Card("Ace",11, R.drawable.ad),
            Card("Ace",11, R.drawable.ah),
            Card("Ace",11, R.drawable.`as`)

    )
    private val deck= mutableListOf<Card>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayerShuffle=MediaPlayer.create(this, R.raw.shuffle)
        mediaPlayerDeal=MediaPlayer.create(this, R.raw.deal)

        hideSystemUI()
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->

            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                val handler = Handler()
                handler.postDelayed({
                    hideSystemUI()
                }, 1000)
            }
        }

//



        hit=findViewById(R.id.hit)
        stand=findViewById(R.id.stand)
        ll=findViewById(R.id.layoutDealer)
        llp=findViewById(R.id.layoutPlayer)
        plus=findViewById(R.id.plus)
        minus=findViewById(R.id.minus)
        potTV=findViewById(R.id.Pot)
        playerS=findViewById(R.id.PlayerSum)
        dealerS=findViewById(R.id.DealerSum)
        bankTV=findViewById(R.id.bank)
        restart=findViewById(R.id.restart)
        llpscroll=findViewById(R.id.playerscroll)
        dealerscroll=findViewById(R.id.dealerscroll)
        gameCount=findViewById(R.id.gameCount)
        deck.addAll(deckOriginal)

        mediaPlayerShuffle?.start()
        deck.shuffle()



        plus.setOnClickListener{
            if(pot+10>bank ){plus.isClickable=false}
            else {
                pot += 10
                val dollar: String = "BET: $"
                val dollarVal: String = pot.toString()
                potTV.setText(dollar + dollarVal)
            }

        }

        plus.setOnLongClickListener{
            if(pot+100>bank){plus.isClickable=false}
            else {
                pot += 100
                val dollar: String = "BET: $"
                val dollarVal: String = pot.toString()
                potTV.setText(dollar + dollarVal)
            }
                true

        }
        minus.setOnClickListener{
            if(pot-10<0) {minus.isClickable=false}
            else {
                pot -= 10
                val dollar: String = "BET: $"
                val dollarVal: String = pot.toString()
                potTV.setText(dollar + dollarVal)
            }
        }
        minus.setOnLongClickListener{
            if(pot-100<0) {}
            else {
                pot -= 100
                val dollar: String = "BET: $"
                val dollarVal: String = pot.toString()
                potTV.setText(dollar + dollarVal)
            }
                true

        }

        hit.setOnClickListener{
            plus.isClickable=false
            minus.isClickable=false
            mediaPlayerDeal?.start()
            stand.isVisible=true
            playerS.isVisible=true
            dealerS.isVisible=true
            restart.isVisible=false
             hit.text="HIT"

            if(turn==1)playerTurn()
            else dealerTurnHelper()

        }
        stand.setOnClickListener{
            dealerTurnHelper()
        }
        restart.setOnClickListener{
             var clickChecker:Int=0

            if(bank<50){

                Toast.makeText(baseContext, "BANKRUPT! GG", Toast.LENGTH_LONG).show()
                restart.isClickable=false
                val handler = Handler()
                handler.postDelayed({
                    finish()
                    exitProcess(0)
                }, 5000)


            }
            else
            restartGame()
        }
    }

    private fun playerTurn(){
        if(count<0){
            bank-=pot
            dealerTurn()
            

        }
        bankTV.setText("BANK:$"+bank.toString())
        plus.isClickable=false
        minus.isClickable=false
        potTV.setText("POT: $"+ (2*pot))
        count++
        val imageView=ImageView(this)
        imageView.layoutParams=LinearLayout.LayoutParams(400,400)
        imageView.setImageResource(deck.elementAt(count).card)
        if(deck.elementAt(count).face.equals("Ace")) acepull=true
        llp.addView(imageView)
        llpscroll.post(Runnable { llpscroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT) })
        playersum += deck.elementAt(count).value
        playerS.setText("Player: "+playersum.toString())
        if(playersum>21){
            if(acepull){
                playersum-=10
                playerS.setText("Player: "+playersum.toString())
                acepull=false
            }
            else {
                Toast.makeText(baseContext, "BUST", Toast.LENGTH_LONG).show()

                bankTV.setText("BANK:$" + bank.toString())
                restart.isVisible = true
                hit.isVisible = false
                stand.isVisible = false
            }

        }
        else if(playersum==21){
            Toast.makeText(baseContext, "PLAYER WINS", Toast.LENGTH_LONG).show()
            ++gamesWon
            bank += (pot * 2)
            bankTV.setText("BANK:$" + bank.toString())
            restart.isVisible = true
            hit.isVisible = false
            stand.isVisible = false
        }


    }

    private fun dealerTurnHelper(){
        stand.isVisible=false
        hit.isVisible=false


        while(dealersum<=20 && dealersum<=playersum){

            dealerTurn()
         }
        if(dealersum>21) {
            if(acepull){
                dealersum-=10
                dealerS.setText("Dealer: "+dealersum.toString())
                acepull=false
            }
            else {
                Toast.makeText(baseContext, "PLAYER WINS", Toast.LENGTH_LONG).show()
                ++gamesWon
                bank += (pot * 2)
                bankTV.setText("BANK:$" + bank.toString())
                restart.isVisible = true
                hit.isVisible = false
                stand.isVisible = false
            }
        }
        else if(dealersum>playersum ){
            Toast.makeText(baseContext, "DEALER WINS", Toast.LENGTH_LONG).show()


            restart.isVisible=true
            hit.isVisible=false
            stand.isVisible=false
        }
    }

    private fun dealerTurn(){

        plus.isClickable=false
        minus.isClickable=false
        count++
        val imageView=ImageView(this)
        imageView.layoutParams=LinearLayout.LayoutParams(400,400)
        imageView.setImageResource(deck.elementAt(count).card)


        ll.addView(imageView)
        dealerscroll.post(Runnable { dealerscroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT) })
        dealersum += deck.elementAt(count).value
        dealerS.setText("Dealer: "+dealersum.toString())

    }


    private fun restartGame(){
        ++gameCounter
        gameCount.setText(gamesWon.toString()+"/"+gameCounter.toString())
        ll.removeAllViews()
        llp.removeAllViews()
        turn=1
        count=-1
        pot=50
        potTV.setText("BET: $50")
        plus.isClickable=true
        minus.isClickable=true
//        plus.isVisible=true
//        minus.isVisible=true

        mediaPlayerShuffle?.start()
        deck.shuffle()

        playersum=0
        playerS.setText("Player: "+playersum.toString())
        dealersum=0
        dealerS.setText("Dealer: "+dealersum.toString())
        hit.text="START"
        hit.isVisible=true
        stand.isVisible=false
        restart.isVisible=false



    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

}