/*
 * Copyright (C) 2014 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tron.keystore;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author venshine
 */
public class StringUtils {

  /**
   * Judge whether each character of the string equals
   */
  public static boolean isCharEqual(char[] str) {
    char c0 = str[0];
    for (char c : str) {
      if (c != c0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Determines if the string is a digit
   */
  public static boolean isNumeric(char[] str) {
    for (char c : str) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * a Contains b , return true
   *
   * @return boolean
   */
  public static boolean isContains(char[] a, char[] b) {
    if (ArrayUtils.isEmpty(a) || ArrayUtils.isEmpty(b)) {
      return false;
    }

    int alen = a.length;
    int blen = b.length;

    for (int i = 0; i < alen; i++) {
      if (alen-i < blen) {
        return false;
      }
      int j;
      for (j = 0; j < blen; j++) {
        if (a[i + j] != b[j]) {
          break;
        }
      }
      if (j == blen) {
        return true;
      }
    }
    return false;
  }

  public static void clear(char[] a) {
    if (ArrayUtils.isEmpty(a)) {
      return;
    }
    for (int i = 0; i < a.length; i++) {
      a[i] = 0;
    }
  }

  public static void clear(byte[] a) {
    if (ArrayUtils.isEmpty(a)) {
      return;
    }
    for (int i = 0; i < a.length; i++) {
      a[i] = 0;
    }
  }

  /**
   * char to utf-8 bytes
   */
  public static byte[] char2Byte(char[] a) {
    int len = 0;
    for (char c : a) {
      if (c > 0x7FF) {
        len += 3;
      } else if (c > 0x7F) {
        len += 2;
      } else {
        len++;
      }
    }

    byte[] result = new byte[len];
    int i = 0;
    for (char c : a) {
      if (c > 0x7FF) {
        result[i++] = (byte) (((c >> 12) & 0x0F) | 0xE0);
        result[i++] = (byte) (((c >> 6) & 0x3F) | 0x80);
        result[i++] = (byte) ((c & 0x3F) | 0x80);
      } else if (c > 127) {
        result[i++] = (byte) (((c >> 6) & 0x1F) | 0xC0);
        result[i++] = (byte) ((c & 0x3F) | 0x80);
      } else {
        result[i++] = (byte) (c & 0x7F);
      }
    }
    return result;
  }
}
