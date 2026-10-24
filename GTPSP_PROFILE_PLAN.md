# GT PSP profile plan (not implemented yet)

A profile for a static recompiler is game-specific. The reusable recompiler is only one part.

## Unknowns that must be measured
- Executable/module layout and entry points for the exact region/revision.
- Required firmware imports (NIDs), thread/callback behavior, file access and timing.
- Allegrex instruction coverage and VFPU usage in reachable code.
- Graphics Engine display-list behavior and texture/framebuffer formats.
- Audio path, controller mapping, save-data format and game-specific services.

## Bring-up sequence
1. Analyze an authorized dump with a chosen recompiler's own tools.
2. Build a function/coverage report; do not assume every executable is handled.
3. Generate native code and compile it for a host target.
4. Add one missing system-call behavior at a time, with tests.
5. Start with boot/menu behavior before attempting a race.
6. Compare frame output, audio and game state against a separate reference emulator.
7. Add Android host integration and ARM64 build.
8. Test on a real device and record crashes/performance.

## Current state in this kit
No generated game code, no firmware HLE, no GE renderer, no audio engine, and no game profile are included.
