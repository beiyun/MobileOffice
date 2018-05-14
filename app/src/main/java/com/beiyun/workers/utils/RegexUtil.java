package com.beiyun.workers.utils;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Writer:carlos
 * @Company:zqht
 * @Time:2016-4-7上午10:04:03
 * @Description:正则校验相关类
 */
public class RegexUtil {
	public static final int MAN = 1, WOMEN = 2, EMPTY = -1;
	static String urlRegex = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
	/**
	 * 手机号的正则
	 */
	public static String mobilePatternStr = "^1(3[0-9]|4[5-7]|5[0-35-9]|8[0-9]|70)\\\\d{8}$";
	/**
	 * 电子邮箱正则
	 */
	public static String emailPatternStr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	/**
	 * 身份证正则表达式(15位)
	 */
	public static String isIDCard15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	/**
	 * 身份证正则表达式(18位)
	 */
	public static String isIDCard18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
	/**
	 * 是否是一个姓名校验
	 */
	public static String trueNamePattern = "^[\\u4E00-\\u9FA5A-Za-z.·]+$";
	/**
	 * 验证是否是数字的校验
	 */
	public static String numberPatternStr = "\\d+(.\\d+)?|-\\d+(.\\d+)?$";
	/**
	 * 校验联通手机号
	 */
	public static String unionPhonePatternStr = "^1(30|31|32|45|55|56|86|76|85)\\d{8}|1709\\d{7}$";
	
	/**
	 * 密码(不能全是下划线，数字，字母，两者及以上组合)
	 */
	public static String passwordPatternStr = "^(?![_]+$)(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$";
	/**
	 * 电话号码(手机和固话)
	 */
	public static String phonePatternStr = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}|[0]{1}[0-9]{2,3}[0-9]{7,8}$";
	/**
	 * 校验是否是一个真实的数字
	 * @param validateNum 比对内容
	 * @return boolean
	 */
	public static boolean verifyTrueNumber(String validateNum) {
		Pattern pattern = Pattern.compile(numberPatternStr);
		Matcher matcher = pattern.matcher(validateNum);
		return matcher.matches();
	}

	/**
	 * 校验是否是一个真实的数字(多个)
	 * @param validateNums
	 * @return boolean
	 */
	public static boolean verifyTrueNumber(String... validateNums) {
		boolean isMatchedPhone = false;
		Pattern regexphone = Pattern.compile(numberPatternStr);
		int length = validateNums.length;
		for (int i = 0; i < length; i++) {
			Matcher matcherphone = regexphone.matcher(validateNums[i]);
			isMatchedPhone = matcherphone.matches();
			if (!isMatchedPhone)
				return false;
		}
		return isMatchedPhone;
	}

	/**
	 * @Description:验证手机号
	 * @param phoneNum
	 * @return
	 * @Throws
	 */
	public static boolean verifyMobilePhone(String phoneNum) {
		Pattern regexPhone = Pattern.compile(mobilePatternStr);
		Matcher matcherPhone = regexPhone.matcher(phoneNum);
		return matcherPhone.matches();
	}
	/**
	 * @Description:验证联通手机号
	 * @param phoneNum
	 * @return
	 * @Throws
	 */
	public static boolean verifyUnionPhone(String phoneNum) {
		Pattern regexPhone = Pattern.compile(mobilePatternStr);
		Matcher matcherPhone = regexPhone.matcher(phoneNum);
		return matcherPhone.matches();
	}
	/**
	 * @Description:验证电话号码(手机，固话)
	 * @param phoneNum
	 * @return
	 * @Throws
	 */
	public static boolean verifyPhone(String phoneNum) {
		Pattern regexPhone = Pattern.compile(phonePatternStr);
		Matcher matcherPhone = regexPhone.matcher(phoneNum);
		return matcherPhone.matches();
	}
	/**
	 * @Description:验证邮箱
	 * @param Email
	 * @return
	 * @Throws
	 */

	public static boolean verifyEmail(String Email) {
		Pattern regexphone = Pattern.compile(emailPatternStr);
		Matcher matchermail = regexphone.matcher(Email);
		boolean isMatchedmail = matchermail.matches();
		return isMatchedmail;
	}

	/**
	 * 验证真实姓名
	 * @param trueName
	 * @return
     */

	public static boolean verifyTrueName(String trueName) {
		Pattern regexName = Pattern.compile(trueNamePattern);
		Matcher matcherName= regexName.matcher(trueName);
		return matcherName.matches();
	}
	/**
	 * @Description: 根据身份证获取性别
	 * @param idCard
	 * @return
	 * @Throws
	 */
	public static int getSexByIDCard(String idCard) {
		String sex = "";
		if (TextUtils.isEmpty(idCard)){
			return MAN;
		}
		if (idCard.length() == 18) {
			sex = idCard.substring(16, 17);
		} else if (idCard.length() == 15) {
			sex = idCard.substring(14);
		}
		if (TextUtils.isEmpty(sex.trim()))
			return EMPTY;
		if (Integer.parseInt(sex) % 2 == 0)
			return WOMEN;
		return MAN;
	}
	/**
	 * @Description: 从身份证提取出生日期
	 * @param idCard
	 * @return
	 * @Throws
	 */
	public static String getBirthdayByIDCard(String idCard) {
		String birthday = "";
		if (idCard.length() == 18) {
			birthday = idCard.substring(6, 14);
		} else if (idCard.length() == 15) {
			birthday = "19" + idCard.substring(6, 12);
		}
		if (TextUtils.isEmpty(birthday.trim()))
			return "";
		int year = Integer.parseInt(birthday.substring(0, 4));
		int month = Integer.parseInt(birthday.substring(4, 6));
		int day = Integer.parseInt(birthday.substring(6, 8));
		return String.format(Locale.CHINA, "%d-%02d-%02d", year, month, day);
	}
	/**
	 * @Description: 从身份证提取出生日期
	 * @param idCard
	 * @return
	 * @Throws
	 */
	public static String getAgeByIDCard(String idCard) {
		String birthday = "";
		if (idCard.length() == 18) {
			birthday = idCard.substring(6, 14);
		} else if (idCard.length() == 15) {
			birthday = "19" + idCard.substring(6, 12);
		}
		if (TextUtils.isEmpty(birthday.trim()))
			return "";
		int year = Integer.parseInt(birthday.substring(0, 4));
		return String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-year);
	}
	/**
	 * 校验身份证
	 * @param identityCardId 身份证号
	 * @return boolean
	 */
	public static boolean verifyIdentity(String identityCardId) {
		if (TextUtils.isEmpty(identityCardId))
			return false;
		Pattern regexCard15 = Pattern.compile(isIDCard15);
		Matcher matcherCard15 = regexCard15.matcher(identityCardId);
		identityCardId = identityCardId.toLowerCase(Locale.CHINA);
		if (identityCardId.length() == 18) {
			if (checkXIdentityCard(identityCardId, identityCardId.substring(17))) {
				identityCardId = identityCardId.replace('x', '2');
			} else {
				return false;
			}
		}
		Pattern regexCard18 = Pattern.compile(isIDCard18);
		Matcher matcherCard18 = regexCard18.matcher(identityCardId);
		boolean isMatchedCard15 = matcherCard15.matches();
		boolean isMatchedCard18 = matcherCard18.matches();
		if (isMatchedCard15 || isMatchedCard18)
			return true;
		return false;
	}

	/**
	 * 校验身份证号末位的X
	 * @param identityCard 身份证号
	 * @param endNum 最后一位
	 * @return boolean
	 */
	public static boolean checkXIdentityCard(String identityCard, String endNum) {
		int[] CoefficientNum = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,
				4, 2};
		int Summation = 0;
		char[] chararray = identityCard.substring(0, identityCard.length() - 1)
				.toCharArray();
		for (int i = 0; i < 17; i++) {
			if (!Character.isDigit(chararray[i]))
				return false;
			Summation += Integer.parseInt(chararray[i] + "")
					* CoefficientNum[i];
		}
		int yu = Summation % 11;
		if (yu == 0 && endNum.equals("1"))
			return true;
		if (yu == 1 && endNum.equals("0"))
			return true;
		if (yu == 2 && endNum.equals("x"))
			return true;
		if (yu == 3 && endNum.equals("9"))
			return true;
		if (yu == 4 && endNum.equals("8"))
			return true;
		if (yu == 5 && endNum.equals("7"))
			return true;
		if (yu == 6 && endNum.equals("6"))
			return true;
		if (yu == 7 && endNum.equals("5"))
			return true;
		if (yu == 8 && endNum.equals("4"))
			return true;
		if (yu == 9 && endNum.equals("3"))
			return true;
		if (yu == 10 && endNum.equals("2"))
			return true;
		return false;

	}


	public static boolean verifyBankAccount(String bankNumber){

		if(bankNumber.length() < 15){
			return false;
		}
		return true;
	}
	public static boolean verifyPassword(String psd){
		Pattern regerPsd = Pattern.compile(passwordPatternStr);
		Matcher matcherPsd = regerPsd.matcher(psd);
		return matcherPsd.matches();
	}
}
