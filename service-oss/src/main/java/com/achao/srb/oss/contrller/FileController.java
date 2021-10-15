package com.achao.srb.oss.contrller;

import com.achao.common.exception.BusinessException;
import com.achao.common.result.ResponseEnum;
import com.achao.common.result.ResponseResult;
import com.achao.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/api/oss/file")
public class FileController {

    @Resource
    FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ResponseResult upload(@ApiParam(value = "文件",required = true)@RequestParam("file")MultipartFile file,@ApiParam(value = "模块",required = true)@RequestParam("module")String module){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);
            return ResponseResult.ok().message("文件上传成功").data("url",uploadUrl);
        }catch (IOException e){
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }

    }

    @ApiOperation("删除文件")
    @DeleteMapping("/remove")
    public ResponseResult remove(@ApiParam("要删除的文件")@RequestParam("url")String url){
        fileService.removeFile(url);
        return ResponseResult.ok().message("删除成功");
    }
}
