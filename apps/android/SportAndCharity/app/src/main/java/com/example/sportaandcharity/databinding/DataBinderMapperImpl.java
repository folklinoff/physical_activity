package com.example.sportaandcharity.databinding;

import androidx.databinding.MergedDataBinderMapper;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.example.sportaandcharity.DataBinderMapperImpl());
  }
}
