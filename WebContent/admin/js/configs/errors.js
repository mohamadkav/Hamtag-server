function getErrors(){
	var map = new Object();

	map["EMPTY_NOTIFICATION_TYPE"] = "نوع اعلان انتخاب نشده";
	map["EMPTY_CONTENT"] = "متن ارسالی خالی است";
	map["INVALID_URL"] = "لینک نا معتبر است";
	map["FIELD_EMPTY"] = "لطفا تمام فیلد ها را پر کنید";
	map["NOT_NUMBER"] = "سایز فایل باید عدد باشد";
	map["SERVER_ERROR"] = "خطا در سرور";
	map["EMPTY_TITLE_FIELD"] = "فیلد عنوان خالی است";
	map["EMPTY_SKU_FIELD"] = "فیلد sku خالی است";
	map["EMPTY_PRICE_FIELD"] = "فیلد قیمت خالی است";
	map["EMPTY_ITEMS_FIELD"] = "آیتمی انتخاب نشده!";
	map["EMPTY_MAP_FIELD"] = "فیلد نقشه خالی است";
	map["EMPTY_MAPZOOM_TITLE_FIELD"] = "فیلد عنوان زوم خالی است";
	map["EMPTY_MAPZOOM_LEVEL_FIELD"] = "فیلد سطح زوم خالی است";
	map["EMPTY_FILE_CHECKSUM_FIELD"] = "فیلد checksum فایل خالی است";
	map["EMPTY_FILE_SIZE_FIELD"] = "فیلد سایز فایل خالی است";
	map["EMPTY_FILE_ADDRESS_FIELD"] = "فیلد آدرس فایل خالی است";
	map["NOTHING_SELECTED"] = "لطفا گزینه مور نظر خود را انتخاب کنید";
	map["EMPTY_TRIGGER_TIME"] = "تاریخ نمایس اعلان خالی است";

	return map;
}

mainApp.constant("errors" , getErrors());

		// EMPTY_TIME,	EMPTY_CONTENT,EMPTY_URL ,EMPTY_BRAND,
		// NO_MODEL_FOUND, LONG_CONVERSION_EXCEPTION,
		// TIME_FORMAT_EXCEPTION, OS_NOT_FOUND, EMPTY_NOTIFICATION_TYPE,
		// EMPTY_MODEL ,DB_ERROR