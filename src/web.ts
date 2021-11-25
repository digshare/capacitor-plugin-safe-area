import { WebPlugin } from '@capacitor/core';

import type { SafeArea, SafeAreaPlugin } from './definitions';

export class SafeAreaWeb extends WebPlugin implements SafeAreaPlugin {
  async getSafeArea(): Promise<SafeArea> {
    return {
      top: 0,
      left: 0,
      bottom: 0,
      right: 0,
    }
  }

  async getStatusBarHeight(): Promise<{
    value: number;
  }> {
    return {
      value: 0,
    };
  }

  async getNavigationBarHeight(): Promise<{
    value: number;
  }> {
    return {
      value: 0,
    };
  }
}
