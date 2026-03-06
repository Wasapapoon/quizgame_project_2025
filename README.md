<img width="1693" height="1215" alt="logo" src="https://github.com/user-attachments/assets/609895e1-bc87-457e-8d88-61503e98f184" />


## Introduction
Code Arena is an immersive image-based puzzle game developed using JavaFX, heavily inspired by the popular Thai television program "Da Vinci Games" (ดาวินชี เกมถอดรหัส). The game challenges players to decode hidden meanings, idioms, or specific subjects from evolving visual puzzles under intense time pressure, testing both cognitive decoding skills and quick reflexes. Featuring a dynamic hint system that mirrors the escalating clues of the original TV format, the game provides a polished experience with integrated sound effects and a responsive UI that adapts to every move.
The game features four distinct modes: EASY, MEDIUM, HARD, and BATTLE, designed to cater to both solo and competitive playstyles. The EASY, MEDIUM, and HARD modes are tailored for single-player challenges, where questions gradually increase in difficulty to keep players sharp and test their knowledge across various topics. For those seeking a more intense experience, the BATTLE mode introduces a head-to-head competition for two players, utilizing a professional "Buzz-in" system where the Tab and Enter keys are used to race for the right to answer and claim victory in the arena.

## Rules
### **General Rules**
* **Decoding Puzzles:** Players must identify the correct Thai word or phrase based on the visual puzzle displayed on the screen.
* **Timer & Hint System:** Each level has a specific time limit. As the clock ticks down, hints will automatically appear to assist the player in decoding the image.
* **Life System:** Every player starts with 3 hearts. If the timer reaches zero and the player has not provided a correct answer, it results in the loss of one heart.
* **Input Locking:** Once a player commits to an answer, the input field is evaluated, and the results are final for that specific turn.

### **Mode-Specific Rules**
* **Single-Player Modes (EASY, MEDIUM, HARD):** Solve as many puzzles as possible to achieve the highest score. The complexity of the images and the speed of the timer vary based on the selected difficulty.
* **BATTLE Mode (2 Players):** This is a competitive head-to-head mode where players must "buzz in" to gain the right to type an answer.
    * **Player A:** Press the **Tab** key to lock the arena and start typing.
    * **Player B:** Press the **Enter** key to lock the arena and start typing.
    * **Exclusive Access:** Only the player who buzzes in first is allowed to enter an answer. The opponent's input field remains disabled until the next question.
      
## Example

* This is the title screen. Press the Play button to start the game or the Exit button to leave.  
<img width="587" height="327" alt="Screenshot 2026-03-06 222727" src="https://github.com/user-attachments/assets/3cb656dc-b895-423b-8a14-0e96239392ed" />

* After pressing the Play button, you can choose the game mode: Easy, Medium, Hard, or Battle. You can also press the Back button to return to the title screen
<img width="587" height="336" alt="image" src="https://github.com/user-attachments/assets/65e3fc7e-cdcb-4f14-aa08-ca32f6e8923c" />

* After selecting a game mode, the game begins, offering both a Single-Player Mode to solve puzzles individually across EASY, MEDIUM, and HARD levels, and a Two-Player BATTLE Mode.
<img width="481" height="629" alt="image" src="https://github.com/user-attachments/assets/eb47e358-b46a-49bb-8fa2-574d1b556017" />

* In BATTLE Mode, players compete using a "Buzz-in" system where Player A presses Tab and Player B presses Enter to claim the right to answer. Once a player successfully buzzes in, their input field is enabled while the opponent's remains locked.
<img width="371" height="701" alt="image" src="https://github.com/user-attachments/assets/29b2e607-3cb4-465c-8b73-206b55c18f9f" />

* If you get stuck on a question, the hint will reveal itself automatically when the timer reaches a determined time. Initially, players see only the puzzle images, but as the countdown hits predetermined marks, specific text hints appear below each image to assist in decoding the hidden phrase. 
<img width="468" height="597" alt="image" src="https://github.com/user-attachments/assets/1dd7352a-a752-4b05-8c83-98693ea96ccd" />

* The transition to the next screen depends on your performance during the round. If you provide a correct answer, you proceed immediately without any penalty to your HP. However, if your answer is incorrect, you must continue attempting the puzzle until you either solve it or the timer reaches zero.
<img width="587" height="151" alt="image" src="https://github.com/user-attachments/assets/9f6b1bfb-4b3d-4a59-bdb6-76a4f92d3581" />

* If the time runs out before a correct answer is provided, one heart will be deducted from your HP before the game moves to the next challenge.
<img width="588" height="155" alt="image" src="https://github.com/user-attachments/assets/422028ff-43cc-4cc8-a879-7ea5ea987bd1" />

* In BATTLE Mode, if one player successfully buzzes in and provides the correct answer, the opponent immediately loses one heart (HP).
<img width="560" height="156" alt="image" src="https://github.com/user-attachments/assets/ba444d2f-26ee-44fc-8ccd-c67b499e50dd" />

* If the timer reaches zero and neither player has provided a correct answer, both Player A and Player B will lose one heart (HP) simultaneously.
<img width="579" height="160" alt="image" src="https://github.com/user-attachments/assets/9577b326-95f6-4770-a3ca-7f1b19fbe647" />

* The result screen reflects your performance throughout the challenge. For single-player modes (EASY, MEDIUM, and HARD), successfully completing with at least one heart remaining will display a "YOU WIN" message. However, if your HP reaches zero before finishing, the screen will show "TRY AGAIN".
<img width="571" height="153" alt="image" src="https://github.com/user-attachments/assets/887dcee4-eb80-42e9-ba8b-2843b4cee5ed" />

* In BATTLE Mode, if player A has more hearts than player B, the screen displays "PLAYER A WIN," whereas if player B has the higher HP, it shows "PLAYER B WIN". In the event that both players finish with an equal HP, the result will be a "DRAW".
<img width="388" height="708" alt="image" src="https://github.com/user-attachments/assets/8ded1781-ed66-4c63-9fde-d2daa8f99931" />

* When you click the “Custom Puzzles” button in the titlescreen, it navigates you to the Custom Puzzles screen. This screen provides a dedicated interface for users to manage and expand the game's content through a custom puzzle system.
<img width="439" height="247" alt="image" src="https://github.com/user-attachments/assets/6d0ff895-c4b7-41ca-a49a-ae257dc83de0" />

* The Custom Puzzles screen allows users to dynamically add the new game's content by creating their own puzzles. This system is divided into a detailed creation form and management list.
<img width="446" height="252" alt="image" src="https://github.com/user-attachments/assets/bc160ea7-3b77-4c3b-8caa-f12dd6cd9ed7" />
<img width="434" height="245" alt="image" src="https://github.com/user-attachments/assets/50e4bce1-663a-4487-8ab8-86198802c2e8" />

* The Custom Puzzles screen allows users to manage their self-created content, including the ability to permanently remove puzzles from the game.
<img width="575" height="156" alt="image" src="https://github.com/user-attachments/assets/0c770a99-5ce7-436e-9e32-79411ca73d37" />



## Class Diagram

<img width="1233" height="674" alt="Screenshot 2026-03-06 215643" src="https://github.com/user-attachments/assets/2d5f4081-c214-4d67-930d-b20a99dbabfd" />

<img width="1213" height="617" alt="Screenshot 2026-03-06 215733" src="https://github.com/user-attachments/assets/c329689f-751e-49bd-9ce7-c3a1db6ffd5c" />

<img width="1202" height="581" alt="Screenshot 2026-03-06 215855" src="https://github.com/user-attachments/assets/393bf077-256b-433c-ba02-1f44cc2a9863" />

<img width="700" height="823" alt="Screenshot 2026-03-06 215906" src="https://github.com/user-attachments/assets/58c20991-1038-4e28-8d0b-e4c64667f73f" />


## Presentation link
https://www.youtube.com/watch?v=qQ1YNRBFUjs

## Javadocs link
javadocs/index.html
