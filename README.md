# GT PSP Native Port Kit (Android starter)

This is a **buildable Android app project scaffold**, not a playable Gran Turismo PSP port.

## What this kit does
- Android app UI for selecting a legally obtained PSP ISO/CSO/PBP file.
- Basic ISO9660 sector scan to look for `PSP_GAME` / `UMD_DATA.BIN` strings.
- Native C++ library wired through Android NDK/JNI as the starting point for native code.
- A documented profile/workflow outline for researching a real static-recompilation port.

## What it does NOT do
- It does not run the game.
- It does not decrypt `EBOOT.BIN`, recompile the full GT PSP executable, implement PSP firmware calls, or render GT PSP graphics/audio.
- It does not include GT PSP game files, Sony firmware, decryption keys, or generated code from the game.

A real native port needs a game-specific profile and a lot of work beyond this scaffold. The public PSPRecomp framework itself describes game-specific profiles, runtime behavior, graphics integration and generated code as separate requirements:
https://github.com/jessicanataliagta/PSPRecomp
Another PSP recompilation toolkit:
https://github.com/sp00nznet/psprecomp

## Build
1. Open this folder in Android Studio (current stable).
2. Install Android SDK Platform 35 and Android NDK (side-by-side) from SDK Manager.
3. Let Gradle sync.
4. Build > Build Bundle(s) / APK(s) > Build APK(s).

The included GitHub Actions workflow can build a debug APK in the cloud:
1. Create a new GitHub repository and upload the contents of this ZIP.
2. Open the repository's **Actions** tab and select **Android debug APK**.
3. Tap **Run workflow** (or push to `main`/`master`).
4. When it finishes, open the workflow run and download the `gtpsp-native-port-kit-debug` artifact.

This requires a GitHub account and internet access. The resulting APK is only the scaffold app, not a playable GT PSP port.

## Next technical milestones
1. Test ISO/PBP identification against user-owned dumps.
2. Extract and inspect the game's executable using an appropriate PSP toolchain.
3. Pick and validate a recompiler framework and create a GT PSP-specific profile.
4. Implement/verify required PSP HLE calls and Allegrex/VFPU behavior.
5. Implement graphics (GE), audio, input, timing and save data.
6. Compare behavior against a PSP emulator as a test oracle.
7. Build and test on real Android ARM64 hardware.

## Legal / source note
Only use game files you are authorized to use. This repository contains no commercial game data.
