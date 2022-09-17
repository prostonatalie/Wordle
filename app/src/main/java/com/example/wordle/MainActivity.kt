package com.example.wordle

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.color
import com.example.wordle.MainActivity.FourLetterWordList.getRandomFourLetterWord


class MainActivity : AppCompatActivity() {

    var wordToGuess = getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        val button = findViewById<Button>(R.id.button)

        val editedText = findViewById<EditText>(R.id.editText)
        //var guess = editedText.text.toString()

        val guess1Word = findViewById<TextView>(R.id.textViewGuess1Word)
        val guess1CheckWord = findViewById<TextView>(R.id.textViewGuess1CheckWord)

        val guess2Word = findViewById<TextView>(R.id.textViewGuess2Word)
        val guess2CheckWord = findViewById<TextView>(R.id.textViewGuess2CheckWord)

        val guess3Word = findViewById<TextView>(R.id.textViewGuess3Word)
        val guess3CheckWord = findViewById<TextView>(R.id.textViewGuess3CheckWord)
        var counter = 1

        val word = findViewById<TextView>(R.id.textViewWord)
        word.visibility = View.GONE
        word.setText(wordToGuess).toString()

        var won = false

        button.setOnClickListener {
            // Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()

            if (counter == 1) {

                var guess = editedText.text.toString().uppercase()
                guess1Word.text = guess
                guess1CheckWord.setText(checkGuess(guess))
                if (guess1CheckWord.text == "OOOO") {
                    won = true
                }
            }
            else if (counter == 2) {
                var guess = editedText.text.toString().uppercase()
                guess2Word.text = guess
                guess2CheckWord.setText(checkGuess(guess))
                if (guess2CheckWord.text == "OOOO") {
                    won = true
                }
            }
            else if (counter == 3) {
                var guess = editedText.text.toString().uppercase()
                guess3Word.text = guess
                guess3CheckWord.setText(checkGuess(guess))
                if (guess3CheckWord.text == "OOOO") {
                    won = true
                }
                else {
                    Toast.makeText(it.context, "Sorry, you lost!", Toast.LENGTH_SHORT).show()
                    button.setText("Restart").toString()
                    word.visibility = View.VISIBLE
                    button.setOnClickListener {
                        val intent = intent
                        finish()
                        startActivity(intent)
                    }
                }
            }

            counter++
            editedText.hideKeyboard()
            editedText.getText().clear()

            if (won) {
                Toast.makeText(it.context, "Congratulations! You WON!", Toast.LENGTH_SHORT).show()
                word.visibility = View.VISIBLE
                button.setText("Restart").toString()
                button.setOnClickListener {
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
            }
        }
    }

    object FourLetterWordList {
        // List of most common 4 letter words from: https://7esl.com/4-letter-words/
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

        // Returns a list of four letter words as a list
        fun getAllFourLetterWords(): List<String> {
            return fourLetterWords.split(",")
        }

        // Returns a random four letter word from the list in all caps
        fun getRandomFourLetterWord(): String {
            val allWords = getAllFourLetterWords()
            val randomNumber = (0..allWords.size).shuffled().last()
            return allWords[randomNumber].uppercase()
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */


    private fun checkGuess(guess: String) : String {
        var result = ""

        for (i in 0..3) {

            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}


