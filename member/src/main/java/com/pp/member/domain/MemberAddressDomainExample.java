package com.pp.member.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberAddressDomainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberAddressDomainExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("member_id like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("member_id not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(String value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(String value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(String value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(String value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(String value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(String value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLike(String value) {
            addCriterion("address_id like", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotLike(String value) {
            addCriterion("address_id not like", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<String> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<String> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(String value1, String value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(String value1, String value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagIsNull() {
            addCriterion("default_flag is null");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagIsNotNull() {
            addCriterion("default_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagEqualTo(String value) {
            addCriterion("default_flag =", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagNotEqualTo(String value) {
            addCriterion("default_flag <>", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagGreaterThan(String value) {
            addCriterion("default_flag >", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagGreaterThanOrEqualTo(String value) {
            addCriterion("default_flag >=", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagLessThan(String value) {
            addCriterion("default_flag <", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagLessThanOrEqualTo(String value) {
            addCriterion("default_flag <=", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagLike(String value) {
            addCriterion("default_flag like", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagNotLike(String value) {
            addCriterion("default_flag not like", value, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagIn(List<String> values) {
            addCriterion("default_flag in", values, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagNotIn(List<String> values) {
            addCriterion("default_flag not in", values, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagBetween(String value1, String value2) {
            addCriterion("default_flag between", value1, value2, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andDefaultFlagNotBetween(String value1, String value2) {
            addCriterion("default_flag not between", value1, value2, "defaultFlag");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNull() {
            addCriterion("province_code is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("province_code is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(String value) {
            addCriterion("province_code =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(String value) {
            addCriterion("province_code <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(String value) {
            addCriterion("province_code >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("province_code >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(String value) {
            addCriterion("province_code <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(String value) {
            addCriterion("province_code <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLike(String value) {
            addCriterion("province_code like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotLike(String value) {
            addCriterion("province_code not like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<String> values) {
            addCriterion("province_code in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<String> values) {
            addCriterion("province_code not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(String value1, String value2) {
            addCriterion("province_code between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(String value1, String value2) {
            addCriterion("province_code not between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("city_code is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("city_code <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("city_code >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("city_code >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("city_code <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("city_code <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("city_code like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("city_code not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("city_code in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("city_code not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("city_code between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("city_code not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeIsNull() {
            addCriterion("town_code is null");
            return (Criteria) this;
        }

        public Criteria andTownCodeIsNotNull() {
            addCriterion("town_code is not null");
            return (Criteria) this;
        }

        public Criteria andTownCodeEqualTo(String value) {
            addCriterion("town_code =", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeNotEqualTo(String value) {
            addCriterion("town_code <>", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeGreaterThan(String value) {
            addCriterion("town_code >", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeGreaterThanOrEqualTo(String value) {
            addCriterion("town_code >=", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeLessThan(String value) {
            addCriterion("town_code <", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeLessThanOrEqualTo(String value) {
            addCriterion("town_code <=", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeLike(String value) {
            addCriterion("town_code like", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeNotLike(String value) {
            addCriterion("town_code not like", value, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeIn(List<String> values) {
            addCriterion("town_code in", values, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeNotIn(List<String> values) {
            addCriterion("town_code not in", values, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeBetween(String value1, String value2) {
            addCriterion("town_code between", value1, value2, "townCode");
            return (Criteria) this;
        }

        public Criteria andTownCodeNotBetween(String value1, String value2) {
            addCriterion("town_code not between", value1, value2, "townCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeIsNull() {
            addCriterion("housepark_code is null");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeIsNotNull() {
            addCriterion("housepark_code is not null");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeEqualTo(String value) {
            addCriterion("housepark_code =", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeNotEqualTo(String value) {
            addCriterion("housepark_code <>", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeGreaterThan(String value) {
            addCriterion("housepark_code >", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeGreaterThanOrEqualTo(String value) {
            addCriterion("housepark_code >=", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeLessThan(String value) {
            addCriterion("housepark_code <", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeLessThanOrEqualTo(String value) {
            addCriterion("housepark_code <=", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeLike(String value) {
            addCriterion("housepark_code like", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeNotLike(String value) {
            addCriterion("housepark_code not like", value, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeIn(List<String> values) {
            addCriterion("housepark_code in", values, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeNotIn(List<String> values) {
            addCriterion("housepark_code not in", values, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeBetween(String value1, String value2) {
            addCriterion("housepark_code between", value1, value2, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andHouseparkCodeNotBetween(String value1, String value2) {
            addCriterion("housepark_code not between", value1, value2, "houseparkCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeIsNull() {
            addCriterion("building_code is null");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeIsNotNull() {
            addCriterion("building_code is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeEqualTo(String value) {
            addCriterion("building_code =", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeNotEqualTo(String value) {
            addCriterion("building_code <>", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeGreaterThan(String value) {
            addCriterion("building_code >", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeGreaterThanOrEqualTo(String value) {
            addCriterion("building_code >=", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeLessThan(String value) {
            addCriterion("building_code <", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeLessThanOrEqualTo(String value) {
            addCriterion("building_code <=", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeLike(String value) {
            addCriterion("building_code like", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeNotLike(String value) {
            addCriterion("building_code not like", value, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeIn(List<String> values) {
            addCriterion("building_code in", values, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeNotIn(List<String> values) {
            addCriterion("building_code not in", values, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeBetween(String value1, String value2) {
            addCriterion("building_code between", value1, value2, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andBuildingCodeNotBetween(String value1, String value2) {
            addCriterion("building_code not between", value1, value2, "buildingCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeIsNull() {
            addCriterion("unit_code is null");
            return (Criteria) this;
        }

        public Criteria andUnitCodeIsNotNull() {
            addCriterion("unit_code is not null");
            return (Criteria) this;
        }

        public Criteria andUnitCodeEqualTo(String value) {
            addCriterion("unit_code =", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeNotEqualTo(String value) {
            addCriterion("unit_code <>", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeGreaterThan(String value) {
            addCriterion("unit_code >", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("unit_code >=", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeLessThan(String value) {
            addCriterion("unit_code <", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeLessThanOrEqualTo(String value) {
            addCriterion("unit_code <=", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeLike(String value) {
            addCriterion("unit_code like", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeNotLike(String value) {
            addCriterion("unit_code not like", value, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeIn(List<String> values) {
            addCriterion("unit_code in", values, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeNotIn(List<String> values) {
            addCriterion("unit_code not in", values, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeBetween(String value1, String value2) {
            addCriterion("unit_code between", value1, value2, "unitCode");
            return (Criteria) this;
        }

        public Criteria andUnitCodeNotBetween(String value1, String value2) {
            addCriterion("unit_code not between", value1, value2, "unitCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeIsNull() {
            addCriterion("floor_code is null");
            return (Criteria) this;
        }

        public Criteria andFloorCodeIsNotNull() {
            addCriterion("floor_code is not null");
            return (Criteria) this;
        }

        public Criteria andFloorCodeEqualTo(String value) {
            addCriterion("floor_code =", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeNotEqualTo(String value) {
            addCriterion("floor_code <>", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeGreaterThan(String value) {
            addCriterion("floor_code >", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("floor_code >=", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeLessThan(String value) {
            addCriterion("floor_code <", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeLessThanOrEqualTo(String value) {
            addCriterion("floor_code <=", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeLike(String value) {
            addCriterion("floor_code like", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeNotLike(String value) {
            addCriterion("floor_code not like", value, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeIn(List<String> values) {
            addCriterion("floor_code in", values, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeNotIn(List<String> values) {
            addCriterion("floor_code not in", values, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeBetween(String value1, String value2) {
            addCriterion("floor_code between", value1, value2, "floorCode");
            return (Criteria) this;
        }

        public Criteria andFloorCodeNotBetween(String value1, String value2) {
            addCriterion("floor_code not between", value1, value2, "floorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeIsNull() {
            addCriterion("door_code is null");
            return (Criteria) this;
        }

        public Criteria andDoorCodeIsNotNull() {
            addCriterion("door_code is not null");
            return (Criteria) this;
        }

        public Criteria andDoorCodeEqualTo(String value) {
            addCriterion("door_code =", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeNotEqualTo(String value) {
            addCriterion("door_code <>", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeGreaterThan(String value) {
            addCriterion("door_code >", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("door_code >=", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeLessThan(String value) {
            addCriterion("door_code <", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeLessThanOrEqualTo(String value) {
            addCriterion("door_code <=", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeLike(String value) {
            addCriterion("door_code like", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeNotLike(String value) {
            addCriterion("door_code not like", value, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeIn(List<String> values) {
            addCriterion("door_code in", values, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeNotIn(List<String> values) {
            addCriterion("door_code not in", values, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeBetween(String value1, String value2) {
            addCriterion("door_code between", value1, value2, "doorCode");
            return (Criteria) this;
        }

        public Criteria andDoorCodeNotBetween(String value1, String value2) {
            addCriterion("door_code not between", value1, value2, "doorCode");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNull() {
            addCriterion("province_name is null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNotNull() {
            addCriterion("province_name is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("province_name =", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("province_name <>", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("province_name >", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("province_name >=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("province_name <", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("province_name <=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLike(String value) {
            addCriterion("province_name like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("province_name not like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("province_name in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("province_name not in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("province_name between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("province_name not between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andTownNameIsNull() {
            addCriterion("town_name is null");
            return (Criteria) this;
        }

        public Criteria andTownNameIsNotNull() {
            addCriterion("town_name is not null");
            return (Criteria) this;
        }

        public Criteria andTownNameEqualTo(String value) {
            addCriterion("town_name =", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameNotEqualTo(String value) {
            addCriterion("town_name <>", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameGreaterThan(String value) {
            addCriterion("town_name >", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameGreaterThanOrEqualTo(String value) {
            addCriterion("town_name >=", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameLessThan(String value) {
            addCriterion("town_name <", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameLessThanOrEqualTo(String value) {
            addCriterion("town_name <=", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameLike(String value) {
            addCriterion("town_name like", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameNotLike(String value) {
            addCriterion("town_name not like", value, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameIn(List<String> values) {
            addCriterion("town_name in", values, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameNotIn(List<String> values) {
            addCriterion("town_name not in", values, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameBetween(String value1, String value2) {
            addCriterion("town_name between", value1, value2, "townName");
            return (Criteria) this;
        }

        public Criteria andTownNameNotBetween(String value1, String value2) {
            addCriterion("town_name not between", value1, value2, "townName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameIsNull() {
            addCriterion("housepark_name is null");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameIsNotNull() {
            addCriterion("housepark_name is not null");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameEqualTo(String value) {
            addCriterion("housepark_name =", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameNotEqualTo(String value) {
            addCriterion("housepark_name <>", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameGreaterThan(String value) {
            addCriterion("housepark_name >", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameGreaterThanOrEqualTo(String value) {
            addCriterion("housepark_name >=", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameLessThan(String value) {
            addCriterion("housepark_name <", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameLessThanOrEqualTo(String value) {
            addCriterion("housepark_name <=", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameLike(String value) {
            addCriterion("housepark_name like", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameNotLike(String value) {
            addCriterion("housepark_name not like", value, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameIn(List<String> values) {
            addCriterion("housepark_name in", values, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameNotIn(List<String> values) {
            addCriterion("housepark_name not in", values, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameBetween(String value1, String value2) {
            addCriterion("housepark_name between", value1, value2, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andHouseparkNameNotBetween(String value1, String value2) {
            addCriterion("housepark_name not between", value1, value2, "houseparkName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNull() {
            addCriterion("building_name is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNotNull() {
            addCriterion("building_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameEqualTo(String value) {
            addCriterion("building_name =", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotEqualTo(String value) {
            addCriterion("building_name <>", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThan(String value) {
            addCriterion("building_name >", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("building_name >=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThan(String value) {
            addCriterion("building_name <", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("building_name <=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLike(String value) {
            addCriterion("building_name like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotLike(String value) {
            addCriterion("building_name not like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIn(List<String> values) {
            addCriterion("building_name in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotIn(List<String> values) {
            addCriterion("building_name not in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameBetween(String value1, String value2) {
            addCriterion("building_name between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotBetween(String value1, String value2) {
            addCriterion("building_name not between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNull() {
            addCriterion("unit_name is null");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNotNull() {
            addCriterion("unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNameEqualTo(String value) {
            addCriterion("unit_name =", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotEqualTo(String value) {
            addCriterion("unit_name <>", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThan(String value) {
            addCriterion("unit_name >", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("unit_name >=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThan(String value) {
            addCriterion("unit_name <", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThanOrEqualTo(String value) {
            addCriterion("unit_name <=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLike(String value) {
            addCriterion("unit_name like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotLike(String value) {
            addCriterion("unit_name not like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameIn(List<String> values) {
            addCriterion("unit_name in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotIn(List<String> values) {
            addCriterion("unit_name not in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameBetween(String value1, String value2) {
            addCriterion("unit_name between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotBetween(String value1, String value2) {
            addCriterion("unit_name not between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andFloorNameIsNull() {
            addCriterion("floor_name is null");
            return (Criteria) this;
        }

        public Criteria andFloorNameIsNotNull() {
            addCriterion("floor_name is not null");
            return (Criteria) this;
        }

        public Criteria andFloorNameEqualTo(String value) {
            addCriterion("floor_name =", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameNotEqualTo(String value) {
            addCriterion("floor_name <>", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameGreaterThan(String value) {
            addCriterion("floor_name >", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameGreaterThanOrEqualTo(String value) {
            addCriterion("floor_name >=", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameLessThan(String value) {
            addCriterion("floor_name <", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameLessThanOrEqualTo(String value) {
            addCriterion("floor_name <=", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameLike(String value) {
            addCriterion("floor_name like", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameNotLike(String value) {
            addCriterion("floor_name not like", value, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameIn(List<String> values) {
            addCriterion("floor_name in", values, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameNotIn(List<String> values) {
            addCriterion("floor_name not in", values, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameBetween(String value1, String value2) {
            addCriterion("floor_name between", value1, value2, "floorName");
            return (Criteria) this;
        }

        public Criteria andFloorNameNotBetween(String value1, String value2) {
            addCriterion("floor_name not between", value1, value2, "floorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameIsNull() {
            addCriterion("door_name is null");
            return (Criteria) this;
        }

        public Criteria andDoorNameIsNotNull() {
            addCriterion("door_name is not null");
            return (Criteria) this;
        }

        public Criteria andDoorNameEqualTo(String value) {
            addCriterion("door_name =", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameNotEqualTo(String value) {
            addCriterion("door_name <>", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameGreaterThan(String value) {
            addCriterion("door_name >", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameGreaterThanOrEqualTo(String value) {
            addCriterion("door_name >=", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameLessThan(String value) {
            addCriterion("door_name <", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameLessThanOrEqualTo(String value) {
            addCriterion("door_name <=", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameLike(String value) {
            addCriterion("door_name like", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameNotLike(String value) {
            addCriterion("door_name not like", value, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameIn(List<String> values) {
            addCriterion("door_name in", values, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameNotIn(List<String> values) {
            addCriterion("door_name not in", values, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameBetween(String value1, String value2) {
            addCriterion("door_name between", value1, value2, "doorName");
            return (Criteria) this;
        }

        public Criteria andDoorNameNotBetween(String value1, String value2) {
            addCriterion("door_name not between", value1, value2, "doorName");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkIsNull() {
            addCriterion("address_remark is null");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkIsNotNull() {
            addCriterion("address_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkEqualTo(String value) {
            addCriterion("address_remark =", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotEqualTo(String value) {
            addCriterion("address_remark <>", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkGreaterThan(String value) {
            addCriterion("address_remark >", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("address_remark >=", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkLessThan(String value) {
            addCriterion("address_remark <", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkLessThanOrEqualTo(String value) {
            addCriterion("address_remark <=", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkLike(String value) {
            addCriterion("address_remark like", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotLike(String value) {
            addCriterion("address_remark not like", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkIn(List<String> values) {
            addCriterion("address_remark in", values, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotIn(List<String> values) {
            addCriterion("address_remark not in", values, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkBetween(String value1, String value2) {
            addCriterion("address_remark between", value1, value2, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotBetween(String value1, String value2) {
            addCriterion("address_remark not between", value1, value2, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andOrderByIsNull() {
            addCriterion("order_by is null");
            return (Criteria) this;
        }

        public Criteria andOrderByIsNotNull() {
            addCriterion("order_by is not null");
            return (Criteria) this;
        }

        public Criteria andOrderByEqualTo(String value) {
            addCriterion("order_by =", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByNotEqualTo(String value) {
            addCriterion("order_by <>", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByGreaterThan(String value) {
            addCriterion("order_by >", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByGreaterThanOrEqualTo(String value) {
            addCriterion("order_by >=", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByLessThan(String value) {
            addCriterion("order_by <", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByLessThanOrEqualTo(String value) {
            addCriterion("order_by <=", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByLike(String value) {
            addCriterion("order_by like", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByNotLike(String value) {
            addCriterion("order_by not like", value, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByIn(List<String> values) {
            addCriterion("order_by in", values, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByNotIn(List<String> values) {
            addCriterion("order_by not in", values, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByBetween(String value1, String value2) {
            addCriterion("order_by between", value1, value2, "orderBy");
            return (Criteria) this;
        }

        public Criteria andOrderByNotBetween(String value1, String value2) {
            addCriterion("order_by not between", value1, value2, "orderBy");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andCreateCodeIsNull() {
            addCriterion("create_code is null");
            return (Criteria) this;
        }

        public Criteria andCreateCodeIsNotNull() {
            addCriterion("create_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreateCodeEqualTo(String value) {
            addCriterion("create_code =", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeNotEqualTo(String value) {
            addCriterion("create_code <>", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeGreaterThan(String value) {
            addCriterion("create_code >", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("create_code >=", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeLessThan(String value) {
            addCriterion("create_code <", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeLessThanOrEqualTo(String value) {
            addCriterion("create_code <=", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeLike(String value) {
            addCriterion("create_code like", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeNotLike(String value) {
            addCriterion("create_code not like", value, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeIn(List<String> values) {
            addCriterion("create_code in", values, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeNotIn(List<String> values) {
            addCriterion("create_code not in", values, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeBetween(String value1, String value2) {
            addCriterion("create_code between", value1, value2, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateCodeNotBetween(String value1, String value2) {
            addCriterion("create_code not between", value1, value2, "createCode");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeIsNull() {
            addCriterion("update_code is null");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeIsNotNull() {
            addCriterion("update_code is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeEqualTo(String value) {
            addCriterion("update_code =", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeNotEqualTo(String value) {
            addCriterion("update_code <>", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeGreaterThan(String value) {
            addCriterion("update_code >", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("update_code >=", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeLessThan(String value) {
            addCriterion("update_code <", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeLessThanOrEqualTo(String value) {
            addCriterion("update_code <=", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeLike(String value) {
            addCriterion("update_code like", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeNotLike(String value) {
            addCriterion("update_code not like", value, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeIn(List<String> values) {
            addCriterion("update_code in", values, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeNotIn(List<String> values) {
            addCriterion("update_code not in", values, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeBetween(String value1, String value2) {
            addCriterion("update_code between", value1, value2, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateCodeNotBetween(String value1, String value2) {
            addCriterion("update_code not between", value1, value2, "updateCode");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIsNull() {
            addCriterion("has_deleted is null");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIsNotNull() {
            addCriterion("has_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andHasDeletedEqualTo(Boolean value) {
            addCriterion("has_deleted =", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotEqualTo(Boolean value) {
            addCriterion("has_deleted <>", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedGreaterThan(Boolean value) {
            addCriterion("has_deleted >", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_deleted >=", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedLessThan(Boolean value) {
            addCriterion("has_deleted <", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("has_deleted <=", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIn(List<Boolean> values) {
            addCriterion("has_deleted in", values, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotIn(List<Boolean> values) {
            addCriterion("has_deleted not in", values, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("has_deleted between", value1, value2, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_deleted not between", value1, value2, "hasDeleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}