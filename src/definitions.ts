export interface SafeArea {
  top: number;
  left: number;
  bottom: number;
  right: number;
}

export interface SafeAreaPlugin {
  getSafeArea(): Promise<SafeArea | undefined>;
  getStatusBarHeight(): Promise<{
    value: number;
  }>;
  getNavigationBarHeight(): Promise<{
    value: number;
  }>; 
}
