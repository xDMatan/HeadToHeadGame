package com.example.headtohead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.headtohead.question.GameCollections;
import com.example.headtohead.question.TFquestion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GameActivityTF extends AppCompatActivity implements View.OnClickListener {
    private int player;
    private int turn;
    private FBmodule fBmodule;
    private GameCollections collections;
    private ArrayList<TFquestion> tFCollection;
    private TextView TvQuestion, QuestionNumber;
    private TFquestion CurrentQuestion;
    private Button btnTrue, btnFalse, btnGoBackToMain;
    private Handler handler;
    private boolean WasP1Correct, WasP2Correct;
    private boolean CanGetNextQuestion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_tf);
        fBmodule = new FBmodule(this);
        TvQuestion = findViewById(R.id.TvQuestion);
        fBmodule.TFSetPlayers(this);
        collections = new GameCollections();
        tFCollection = collections.getTFCollection();
        btnTrue = findViewById(R.id.btnTRUE);
        btnTrue.setOnClickListener(this);
        btnFalse = findViewById(R.id.btnFALSE);
        btnFalse.setOnClickListener(this);
        btnGoBackToMain = findViewById(R.id.btnGoBackToMain);
        btnGoBackToMain.setOnClickListener(this);
        handler = new Handler();
        turn = 1;
        player = 0;
        WasP2Correct = false;
        WasP1Correct = false;
        QuestionNumber = findViewById(R.id.TvQuestionNumber);
        CurrentQuestion = new TFquestion("", "", false, false);


    }

    public void StratingPoint(int player) {
        this.player = player;
            btnFalse.setEnabled(false);
            btnTrue.setEnabled(false);

            if (player == 2) {
                GetANewRandomQuestionForP2();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/CurrentQuestion");
                reference.setValue(CurrentQuestion.getQuestion());//מעלה את השאלה לfb
                fBmodule.TFSetQuestion(this);
                TvQuestion.setText("Waiting...");


            }
            if (player == 1) {
                if (turn == 1)
                    TvQuestion.setText("Waiting for players...");
                fBmodule.TFSetQuestion(this);
            }

        }


    public void StartGame(String Question) {
        if (CanGetNextQuestion) {

            if (this.player == 2) {
                if (turn == 1) {
                    TvQuestion.setText("Strating game in 5 seconds");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            QuestionNumber.setVisibility(View.VISIBLE);
                            TvQuestion.setText(CurrentQuestion.getQuestion());
                            btnFalse.setEnabled(true);
                            btnTrue.setEnabled(true);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btnFalse.setEnabled(false);
                                    btnTrue.setEnabled(false);
                                    fBmodule.TFSetWhoWasCorrect(GameActivityTF.this);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (CheckIfSomeoneWon() == 1) {
                                                TvQuestion.setText("LOSER");
                                                MakeGoBackApear();
                                                CanGetNextQuestion=false;
                                            } else if (CheckIfSomeoneWon() == 2) {
                                                TvQuestion.setText("WINNER");
                                                MakeGoBackApear();
                                                CanGetNextQuestion=false;
                                            } else if (CheckIfSomeoneWon() == -1) {
                                                TvQuestion.setText("BOTH OF YOU SHOULD BE ASHAMED");
                                                MakeGoBackApear();
                                                CanGetNextQuestion=false;
                                            } else {
                                                GetANewRandomQuestionForP2();
                                                setTurn(getTurn() + 1);
                                                RestartAnswers();
                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/CurrentQuestion");
                                                reference.setValue(CurrentQuestion.getQuestion());//מעלה את השאלה לfb
                                            }
                                        }

                                    }, 2000);
                                }
                            }, 4000);
                        }
                    }, 5000);
                } else if (turn > 1) {

                    QuestionNumber.setText(Integer.toString(turn));
                    TvQuestion.setText(CurrentQuestion.getQuestion());
                    btnFalse.setEnabled(true);
                    btnTrue.setEnabled(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnFalse.setEnabled(false);
                            btnTrue.setEnabled(false);
                            fBmodule.TFSetWhoWasCorrect(GameActivityTF.this);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (CheckIfSomeoneWon() == 1) {
                                        TvQuestion.setText("LOSER");
                                        MakeGoBackApear();
                                        CanGetNextQuestion=false;
                                    } else if (CheckIfSomeoneWon() == 2) {
                                        TvQuestion.setText("WINNER");
                                        MakeGoBackApear();
                                        CanGetNextQuestion=false;
                                    } else if (CheckIfSomeoneWon() == -1) {
                                        TvQuestion.setText("BOTH OF YOU SHOULD BE ASHAMED");
                                        MakeGoBackApear();
                                        CanGetNextQuestion=false;
                                    } else {
                                        setTurn(getTurn() + 1);
                                        RestartAnswers();
                                        GetANewRandomQuestionForP2();
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/CurrentQuestion");
                                        reference.setValue(CurrentQuestion.getQuestion());//מעלה את השאלה לfb
                                    }
                                }
                            }, 2000);
                        }
                    }, 4000);
                }
            }
            if (this.player == 1) {
                if (!CurrentQuestion.getQuestion().equals(Question)) {
                    for (int i = 0; i < tFCollection.size(); i++) {
                        if (Question.equals(tFCollection.get(i).getQuestion()))
                            CurrentQuestion = tFCollection.get(i);
                    }
                    if (turn == 1) {
                        TvQuestion.setText("Strating game in 5 seconds");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                QuestionNumber.setVisibility(View.VISIBLE);
                                TvQuestion.setText(CurrentQuestion.getQuestion());
                                btnFalse.setEnabled(true);
                                btnTrue.setEnabled(true);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnFalse.setEnabled(false);
                                        btnTrue.setEnabled(false);
                                        fBmodule.TFSetWhoWasCorrect(GameActivityTF.this);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (CheckIfSomeoneWon() == 1) {
                                                    TvQuestion.setText("WINNER");
                                                    MakeGoBackApear();
                                                    CanGetNextQuestion=false;
                                                }
                                                if (CheckIfSomeoneWon() == 2) {
                                                    TvQuestion.setText("LOSER");
                                                    MakeGoBackApear();
                                                    CanGetNextQuestion=false;

                                                }
                                                if (CheckIfSomeoneWon() == -1) {
                                                    TvQuestion.setText("BOTH OF YOU SHOULD BE ASHAMED");
                                                    MakeGoBackApear();
                                                    CanGetNextQuestion=false;
                                                }


                                                setTurn(getTurn() + 1);
                                                setWasP1Correct(false);
                                                setWasP2Correct(false);


                                            }
                                        }, 2000);
                                    }
                                }, 4000);

                            }
                        }, 5000);
                    } else if (turn > 1) {

                        QuestionNumber.setText(Integer.toString(turn));
                        TvQuestion.setText(CurrentQuestion.getQuestion());
                        btnFalse.setEnabled(true);
                        btnTrue.setEnabled(true);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btnFalse.setEnabled(false);
                                btnTrue.setEnabled(false);
                                fBmodule.TFSetWhoWasCorrect(GameActivityTF.this);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (CheckIfSomeoneWon() == 1) {
                                            TvQuestion.setText("WINNER");
                                            MakeGoBackApear();
                                            CanGetNextQuestion=false;
                                        }
                                        if (CheckIfSomeoneWon() == 2) {
                                            TvQuestion.setText("LOSER");
                                            MakeGoBackApear();
                                            CanGetNextQuestion=false;
                                        }
                                        if (CheckIfSomeoneWon() == -1) {
                                            TvQuestion.setText("BOTH OF YOU SHOULD BE ASHAMED");
                                            MakeGoBackApear();
                                            CanGetNextQuestion=false;
                                        }


                                        setTurn(getTurn() + 1);
                                        setWasP1Correct(false);
                                        setWasP2Correct(false);


                                    }
                                }, 2000);
                            }
                        }, 4000);
                    }


                }
            }
        }
    }

    public void GetANewRandomQuestionForP2() {
        Boolean valid = false;
        String CurrentType = CurrentQuestion.getTypeofquestion();

        while (!valid) {
            java.util.Collections.shuffle(this.tFCollection);
            this.CurrentQuestion = tFCollection.get(0);
            if (CurrentType.equals(CurrentQuestion.getTypeofquestion()) == false && CurrentQuestion.getWaseverused() == false) {
                tFCollection.get(0).setWaseverused(true);
                valid = true;
            }

        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnFalse) {
            if (this.CurrentQuestion.getAnswer() == false) {
                SendIfAnswerWasRightToFb(true);
                TvQuestion.setText("CORRECT!!!");
            } else {
                SendIfAnswerWasRightToFb(false);
                TvQuestion.setText("Incorrect!!!");
            }
            btnFalse.setEnabled(false);
            btnTrue.setEnabled(false);
        }
        if (v == btnTrue) {
            if (this.CurrentQuestion.getAnswer() == true) {
                SendIfAnswerWasRightToFb(true);
                TvQuestion.setText("CORRECT!!!");
            } else {
                SendIfAnswerWasRightToFb(false);
                TvQuestion.setText("Incorrect!!!");
            }
            btnTrue.setEnabled(false);
            btnFalse.setEnabled(false);
        }
        if (v == btnGoBackToMain) {
            Intent i = new Intent(this, SignIn.class);
            startActivity(i);
        }
    }

    public void SendIfAnswerWasRightToFb(Boolean WasCorrect) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (this.player == 1) {
            DatabaseReference AnswerStatusp1 = firebaseDatabase.getReference("TFGameControl/answerStatus/p1");
            AnswerStatusp1.setValue(WasCorrect);
        }
        if (this.player == 2) {
            DatabaseReference AnswerStatusp2 = firebaseDatabase.getReference("TFGameControl/answerStatus/p2");
            AnswerStatusp2.setValue(WasCorrect);
        }


    }

    public int CheckIfSomeoneWon() {
        //1 - player 1 won
        //2 - player 2 won
        //0 - both were right, game keep going
        //-1 - both lost
        if (WasP1Correct == true && WasP2Correct == false) {
            return 1;
        }
        if (WasP1Correct == false && WasP2Correct == true)
            return 2;
        if (WasP1Correct == false && WasP2Correct == false)
            return -1;
        return 0;

    }

    public void setWasP2Correct(boolean wasP2Correct) {
        WasP2Correct = wasP2Correct;
    }

    public void setWasP1Correct(boolean wasP1Correct) {
        WasP1Correct = wasP1Correct;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void MakeGoBackApear() {
        btnGoBackToMain.setEnabled(true);
        btnGoBackToMain.setVisibility(View.VISIBLE);
        btnTrue.setVisibility(View.INVISIBLE);
        btnFalse.setVisibility(View.INVISIBLE);
    }
    public void RestartAnswers() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference AnswerStatusp1 = firebaseDatabase.getReference("TFGameControl/answerStatus/p1");
        AnswerStatusp1.setValue(null);
        DatabaseReference AnswerStatusp2 = firebaseDatabase.getReference("TFGameControl/answerStatus/p2");
        AnswerStatusp2.setValue(null);
        setWasP1Correct(false);
        setWasP2Correct(false);
    }

    public int getTurn() {
        return turn;
    }
}


