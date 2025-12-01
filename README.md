# Ransomware-Behavior-Simulator
This project safely simulates ransomware behavior for learning and security testing.
It does not spread, damage the system, or target any real user files.

✔ What It Does

Encrypts files only inside C:/TestFiles/ using AES.

Deletes the original files after encrypting (safe copies are still recoverable).

The Detector App (GUI) monitors the folder for unexpected modifications.

When encrypted files are found, it alerts the user and asks:

Restore files?
If approved, the built‑in Decrypter restores all encrypted files automatically.

📁 Project Structure

Simulator.exe → Encrypts files in the test folder

Detector.exe → Detects encryption, alerts user, and restores files

readme.md → You are reading it

TestFiles/ → Only folder affected

🎯 Purpose

This project is for educational cybersecurity practice, demonstrating:

How ransomware encrypts files

How detectors identify suspicious modifications

How recovery tools decrypt files safely

▶ How to Use

Create folder: C:/TestFiles/

Place some sample files inside.

Run Simulator → Files get encrypted.

Run Detector → Alerts you if encryption is detected.

Click Restore → Files are automatically decrypted.

⚠ Safety Notes

Affects only C:/TestFiles/

Uses safe AES encryption

No spreading, no harmful behavior
