package com.beiyun.workers.okhttp.callback;
/**
 * Created by zqht on 2016/7/6 10:29
 * Email:zmm534635184@sina.com
 */
public class BaseInfo<BodyDataInfo> {
	public int resultCode;
	public String reason;
	public DataInfo<BodyDataInfo> data;

	public int getResultCode() {
		return resultCode;
	}

	public String getReason() {
		return reason;
	}


	public DataInfo<BodyDataInfo> getData() {
		return data;
	}
	public void setData(DataInfo<BodyDataInfo> data) {
		this.data = data;
	}

	public static class DataInfo<BodyInfo> {
		public int total;
		public BodyInfo list;
		public String households;//户数
		public String areas;//亩数


		public String getHouseholds() {
			return households;
		}

		public String getAreas() {
			return areas;
		}

		public BodyInfo getList() {
			return list;
		}
		public void setList(BodyInfo list) {
			this.list = list;
		}

		@Override
		public String toString() {
			return "DataInfo{" +
					"total=" + total +
					", list=" + list +
					", households='" + households + '\'' +
					", areas='" + areas + '\'' +
					'}';
		}
	}
}
