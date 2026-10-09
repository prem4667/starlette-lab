STARLETTE LAB - Android WebView project

This project packages the offline pathology LIS HTML inside an Android WebView.
JavaScript and DOM storage are enabled. PDF/print actions call Android's native PrintManager bridge.

IMPORTANT: This ZIP is source code, not a compiled APK. It has not been built or installed on a physical phone in this environment. Test with sample/non-real patient data before clinical use.

Cloud build using a phone (GitHub Actions):
1. Create a GitHub repository in your phone browser.
2. Upload the contents of this folder to the repository root (not the outer folder itself).
3. Open Actions and enable workflows if GitHub asks.
4. Select 'Build STARLETTE LAB APK' and tap Run workflow.
5. When completed, open the run, find Artifacts, and download STARLETTE-LAB-debug-apk.
6. Extract the artifact ZIP and install app-debug.apk. Android may ask permission to install unknown apps.

The app stores records locally on this device. Back up records regularly. The default test ranges require verification by qualified lab personnel before clinical use.
