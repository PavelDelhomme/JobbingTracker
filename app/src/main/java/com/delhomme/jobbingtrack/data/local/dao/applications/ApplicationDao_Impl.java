package com.delhomme.jobbingtrack.data.local.dao.applications;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomRawQuery;
import androidx.room.RoomSQLiteQuery;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SimpleSQLiteQuery;
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class ApplicationDao_Impl implements ApplicationDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<ApplicationEntity> __insertAdapterOfCandidatureEntity;

  private final EntityDeleteOrUpdateAdapter<ApplicationEntity> __updateAdapterOfCandidatureEntity;

  public ApplicationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfCandidatureEntity = new EntityInsertAdapter<ApplicationEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `candidatures` (`id`,`title`,`userId`,`companyId`,`applicationDate`,`platform`,`contractType`,`location`,`applicationType`,`applicationStatus`,`isArchived`,`notes`,`syncHash`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ApplicationEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getUserId());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCompanyId());
        }
        statement.bindLong(5, entity.getApplicationDate());
        if (entity.getPlatform() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getPlatform());
        }
        if (entity.getContractType() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getContractType());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getLocation());
        }
        if (entity.getApplicationType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getApplicationType());
        }
        if (entity.getApplicationStatus() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getApplicationStatus());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(11, _tmp);
        if (entity.getNotes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getSyncHash());
        }
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getCreatedAt());
        statement.bindLong(16, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getArchivedAt());
        }
      }
    };
    this.__updateAdapterOfCandidatureEntity = new EntityDeleteOrUpdateAdapter<ApplicationEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `candidatures` SET `id` = ?,`title` = ?,`userId` = ?,`companyId` = ?,`applicationDate` = ?,`platform` = ?,`contractType` = ?,`location` = ?,`applicationType` = ?,`applicationStatus` = ?,`isArchived` = ?,`notes` = ?,`syncHash` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ApplicationEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getUserId());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCompanyId());
        }
        statement.bindLong(5, entity.getApplicationDate());
        if (entity.getPlatform() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getPlatform());
        }
        if (entity.getContractType() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getContractType());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getLocation());
        }
        if (entity.getApplicationType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getApplicationType());
        }
        if (entity.getApplicationStatus() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getApplicationStatus());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(11, _tmp);
        if (entity.getNotes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getSyncHash());
        }
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getCreatedAt());
        statement.bindLong(16, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(19);
        } else {
          statement.bindText(19, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final ApplicationEntity cand, final Continuation<? super Unit> $completion) {
    if (cand == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfCandidatureEntity.insert(_connection, cand);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final ApplicationEntity cand, final Continuation<? super Unit> $completion) {
    if (cand == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfCandidatureEntity.handle(_connection, cand);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<ApplicationEntity>> getAllForUser(final String userId) {
    final String _sql = "SELECT * FROM candidatures WHERE userId = ? ORDER BY applicationDate DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"candidatures"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfApplicationDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationDate");
        final int _columnIndexOfPlatform = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "platform");
        final int _columnIndexOfContractType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contractType");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfApplicationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationType");
        final int _columnIndexOfApplicationStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationStatus");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ApplicationEntity> _result = new ArrayList<ApplicationEntity>();
        while (_stmt.step()) {
          final ApplicationEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpApplicationDate;
          _tmpApplicationDate = _stmt.getLong(_columnIndexOfApplicationDate);
          final String _tmpPlatform;
          if (_stmt.isNull(_columnIndexOfPlatform)) {
            _tmpPlatform = null;
          } else {
            _tmpPlatform = _stmt.getText(_columnIndexOfPlatform);
          }
          final String _tmpContractType;
          if (_stmt.isNull(_columnIndexOfContractType)) {
            _tmpContractType = null;
          } else {
            _tmpContractType = _stmt.getText(_columnIndexOfContractType);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpApplicationType;
          if (_stmt.isNull(_columnIndexOfApplicationType)) {
            _tmpApplicationType = null;
          } else {
            _tmpApplicationType = _stmt.getText(_columnIndexOfApplicationType);
          }
          final String _tmpApplicationStatus;
          if (_stmt.isNull(_columnIndexOfApplicationStatus)) {
            _tmpApplicationStatus = null;
          } else {
            _tmpApplicationStatus = _stmt.getText(_columnIndexOfApplicationStatus);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ApplicationEntity(_tmpId,_tmpTitle,_tmpUserId,_tmpCompanyId,_tmpApplicationDate,_tmpPlatform,_tmpContractType,_tmpLocation,_tmpApplicationType,_tmpApplicationStatus,_tmpIsArchived,_tmpNotes,_tmpSyncHash,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ApplicationEntity>> getAllActiveForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM candidatures\n"
            + "       WHERE userId    = ?\n"
            + "         AND isDeleted = 0\n"
            + "         AND isArchived= 0\n"
            + "      ORDER BY applicationDate DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"candidatures"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfApplicationDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationDate");
        final int _columnIndexOfPlatform = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "platform");
        final int _columnIndexOfContractType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contractType");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfApplicationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationType");
        final int _columnIndexOfApplicationStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationStatus");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ApplicationEntity> _result = new ArrayList<ApplicationEntity>();
        while (_stmt.step()) {
          final ApplicationEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpApplicationDate;
          _tmpApplicationDate = _stmt.getLong(_columnIndexOfApplicationDate);
          final String _tmpPlatform;
          if (_stmt.isNull(_columnIndexOfPlatform)) {
            _tmpPlatform = null;
          } else {
            _tmpPlatform = _stmt.getText(_columnIndexOfPlatform);
          }
          final String _tmpContractType;
          if (_stmt.isNull(_columnIndexOfContractType)) {
            _tmpContractType = null;
          } else {
            _tmpContractType = _stmt.getText(_columnIndexOfContractType);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpApplicationType;
          if (_stmt.isNull(_columnIndexOfApplicationType)) {
            _tmpApplicationType = null;
          } else {
            _tmpApplicationType = _stmt.getText(_columnIndexOfApplicationType);
          }
          final String _tmpApplicationStatus;
          if (_stmt.isNull(_columnIndexOfApplicationStatus)) {
            _tmpApplicationStatus = null;
          } else {
            _tmpApplicationStatus = _stmt.getText(_columnIndexOfApplicationStatus);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ApplicationEntity(_tmpId,_tmpTitle,_tmpUserId,_tmpCompanyId,_tmpApplicationDate,_tmpPlatform,_tmpContractType,_tmpLocation,_tmpApplicationType,_tmpApplicationStatus,_tmpIsArchived,_tmpNotes,_tmpSyncHash,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ApplicationEntity>> getArchivedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM candidatures\n"
            + "       WHERE userId    = ?\n"
            + "         AND isArchived= 1\n"
            + "      ORDER BY applicationDate DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"candidatures"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfApplicationDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationDate");
        final int _columnIndexOfPlatform = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "platform");
        final int _columnIndexOfContractType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contractType");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfApplicationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationType");
        final int _columnIndexOfApplicationStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationStatus");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ApplicationEntity> _result = new ArrayList<ApplicationEntity>();
        while (_stmt.step()) {
          final ApplicationEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpApplicationDate;
          _tmpApplicationDate = _stmt.getLong(_columnIndexOfApplicationDate);
          final String _tmpPlatform;
          if (_stmt.isNull(_columnIndexOfPlatform)) {
            _tmpPlatform = null;
          } else {
            _tmpPlatform = _stmt.getText(_columnIndexOfPlatform);
          }
          final String _tmpContractType;
          if (_stmt.isNull(_columnIndexOfContractType)) {
            _tmpContractType = null;
          } else {
            _tmpContractType = _stmt.getText(_columnIndexOfContractType);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpApplicationType;
          if (_stmt.isNull(_columnIndexOfApplicationType)) {
            _tmpApplicationType = null;
          } else {
            _tmpApplicationType = _stmt.getText(_columnIndexOfApplicationType);
          }
          final String _tmpApplicationStatus;
          if (_stmt.isNull(_columnIndexOfApplicationStatus)) {
            _tmpApplicationStatus = null;
          } else {
            _tmpApplicationStatus = _stmt.getText(_columnIndexOfApplicationStatus);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ApplicationEntity(_tmpId,_tmpTitle,_tmpUserId,_tmpCompanyId,_tmpApplicationDate,_tmpPlatform,_tmpContractType,_tmpLocation,_tmpApplicationType,_tmpApplicationStatus,_tmpIsArchived,_tmpNotes,_tmpSyncHash,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ApplicationEntity>> getDeletedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM candidatures\n"
            + "       WHERE userId    = ?\n"
            + "         AND isDeleted = 1\n"
            + "      ORDER BY applicationDate DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"candidatures"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfApplicationDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationDate");
        final int _columnIndexOfPlatform = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "platform");
        final int _columnIndexOfContractType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contractType");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfApplicationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationType");
        final int _columnIndexOfApplicationStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationStatus");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ApplicationEntity> _result = new ArrayList<ApplicationEntity>();
        while (_stmt.step()) {
          final ApplicationEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpApplicationDate;
          _tmpApplicationDate = _stmt.getLong(_columnIndexOfApplicationDate);
          final String _tmpPlatform;
          if (_stmt.isNull(_columnIndexOfPlatform)) {
            _tmpPlatform = null;
          } else {
            _tmpPlatform = _stmt.getText(_columnIndexOfPlatform);
          }
          final String _tmpContractType;
          if (_stmt.isNull(_columnIndexOfContractType)) {
            _tmpContractType = null;
          } else {
            _tmpContractType = _stmt.getText(_columnIndexOfContractType);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpApplicationType;
          if (_stmt.isNull(_columnIndexOfApplicationType)) {
            _tmpApplicationType = null;
          } else {
            _tmpApplicationType = _stmt.getText(_columnIndexOfApplicationType);
          }
          final String _tmpApplicationStatus;
          if (_stmt.isNull(_columnIndexOfApplicationStatus)) {
            _tmpApplicationStatus = null;
          } else {
            _tmpApplicationStatus = _stmt.getText(_columnIndexOfApplicationStatus);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ApplicationEntity(_tmpId,_tmpTitle,_tmpUserId,_tmpCompanyId,_tmpApplicationDate,_tmpPlatform,_tmpContractType,_tmpLocation,_tmpApplicationType,_tmpApplicationStatus,_tmpIsArchived,_tmpNotes,_tmpSyncHash,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<ApplicationEntity> getByIdForUser(final String id, final String userId) {
    final String _sql = "SELECT * FROM candidatures WHERE id = ? AND userId = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"candidatures"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfApplicationDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationDate");
        final int _columnIndexOfPlatform = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "platform");
        final int _columnIndexOfContractType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contractType");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfApplicationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationType");
        final int _columnIndexOfApplicationStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "applicationStatus");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ApplicationEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpApplicationDate;
          _tmpApplicationDate = _stmt.getLong(_columnIndexOfApplicationDate);
          final String _tmpPlatform;
          if (_stmt.isNull(_columnIndexOfPlatform)) {
            _tmpPlatform = null;
          } else {
            _tmpPlatform = _stmt.getText(_columnIndexOfPlatform);
          }
          final String _tmpContractType;
          if (_stmt.isNull(_columnIndexOfContractType)) {
            _tmpContractType = null;
          } else {
            _tmpContractType = _stmt.getText(_columnIndexOfContractType);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpApplicationType;
          if (_stmt.isNull(_columnIndexOfApplicationType)) {
            _tmpApplicationType = null;
          } else {
            _tmpApplicationType = _stmt.getText(_columnIndexOfApplicationType);
          }
          final String _tmpApplicationStatus;
          if (_stmt.isNull(_columnIndexOfApplicationStatus)) {
            _tmpApplicationStatus = null;
          } else {
            _tmpApplicationStatus = _stmt.getText(_columnIndexOfApplicationStatus);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _result = new ApplicationEntity(_tmpId,_tmpTitle,_tmpUserId,_tmpCompanyId,_tmpApplicationDate,_tmpPlatform,_tmpContractType,_tmpLocation,_tmpApplicationType,_tmpApplicationStatus,_tmpIsArchived,_tmpNotes,_tmpSyncHash,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object archive(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE candidatures SET isArchived = 1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object softDelete(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE candidatures SET isDeleted  = 1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object restore(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE candidatures SET isDeleted  = 0 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteForever(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("DELETE FROM candidatures WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForUser(final String userId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM candidatures WHERE userId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Flow<List<ApplicationEntity>> getByDateRange(final SimpleSQLiteQuery query) {
    final RoomRawQuery _rawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery();
    final String _sql = _rawQuery.getSql();
    return FlowUtil.createFlow(__db, false, new String[] {"candidatures"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _rawQuery.getBindingFunction().invoke(_stmt);
        final List<ApplicationEntity> _result = new ArrayList<ApplicationEntity>();
        while (_stmt.step()) {
          final ApplicationEntity _item;
          _item = __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesCandidatureEntity(_stmt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public String getTableName() {
    return ApplicationDao.DefaultImpls.getTableName(ApplicationDao_Impl.this);
  }

  @Override
  public String getDateColumn() {
    return ApplicationDao.DefaultImpls.getDateColumn(ApplicationDao_Impl.this);
  }

  @Override
  public Flow<List<ApplicationEntity>> getByDateRangeForUser(final String userId, final long start,
                                                             final long end) {
    return ApplicationDao.DefaultImpls.getByDateRangeForUser(ApplicationDao_Impl.this, userId, start, end);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private ApplicationEntity __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesCandidatureEntity(
      @NonNull final SQLiteStatement statement) {
    final ApplicationEntity _entity;
    final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndex(statement, "id");
    final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndex(statement, "title");
    final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndex(statement, "userId");
    final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndex(statement, "companyId");
    final int _columnIndexOfApplicationDate = SQLiteStatementUtil.getColumnIndex(statement, "applicationDate");
    final int _columnIndexOfPlatform = SQLiteStatementUtil.getColumnIndex(statement, "platform");
    final int _columnIndexOfContractType = SQLiteStatementUtil.getColumnIndex(statement, "contractType");
    final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndex(statement, "location");
    final int _columnIndexOfApplicationType = SQLiteStatementUtil.getColumnIndex(statement, "applicationType");
    final int _columnIndexOfApplicationStatus = SQLiteStatementUtil.getColumnIndex(statement, "applicationStatus");
    final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndex(statement, "isArchived");
    final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndex(statement, "notes");
    final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndex(statement, "syncHash");
    final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndex(statement, "isDeleted");
    final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndex(statement, "createdAt");
    final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndex(statement, "updatedAt");
    final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndex(statement, "deletedAt");
    final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndex(statement, "archivedAt");
    final String _tmpId;
    if (_columnIndexOfId == -1) {
      _tmpId = null;
    } else {
      if (statement.isNull(_columnIndexOfId)) {
        _tmpId = null;
      } else {
        _tmpId = statement.getText(_columnIndexOfId);
      }
    }
    final String _tmpTitle;
    if (_columnIndexOfTitle == -1) {
      _tmpTitle = null;
    } else {
      if (statement.isNull(_columnIndexOfTitle)) {
        _tmpTitle = null;
      } else {
        _tmpTitle = statement.getText(_columnIndexOfTitle);
      }
    }
    final String _tmpUserId;
    if (_columnIndexOfUserId == -1) {
      _tmpUserId = null;
    } else {
      if (statement.isNull(_columnIndexOfUserId)) {
        _tmpUserId = null;
      } else {
        _tmpUserId = statement.getText(_columnIndexOfUserId);
      }
    }
    final String _tmpCompanyId;
    if (_columnIndexOfCompanyId == -1) {
      _tmpCompanyId = null;
    } else {
      if (statement.isNull(_columnIndexOfCompanyId)) {
        _tmpCompanyId = null;
      } else {
        _tmpCompanyId = statement.getText(_columnIndexOfCompanyId);
      }
    }
    final long _tmpApplicationDate;
    if (_columnIndexOfApplicationDate == -1) {
      _tmpApplicationDate = 0;
    } else {
      _tmpApplicationDate = statement.getLong(_columnIndexOfApplicationDate);
    }
    final String _tmpPlatform;
    if (_columnIndexOfPlatform == -1) {
      _tmpPlatform = null;
    } else {
      if (statement.isNull(_columnIndexOfPlatform)) {
        _tmpPlatform = null;
      } else {
        _tmpPlatform = statement.getText(_columnIndexOfPlatform);
      }
    }
    final String _tmpContractType;
    if (_columnIndexOfContractType == -1) {
      _tmpContractType = null;
    } else {
      if (statement.isNull(_columnIndexOfContractType)) {
        _tmpContractType = null;
      } else {
        _tmpContractType = statement.getText(_columnIndexOfContractType);
      }
    }
    final String _tmpLocation;
    if (_columnIndexOfLocation == -1) {
      _tmpLocation = null;
    } else {
      if (statement.isNull(_columnIndexOfLocation)) {
        _tmpLocation = null;
      } else {
        _tmpLocation = statement.getText(_columnIndexOfLocation);
      }
    }
    final String _tmpApplicationType;
    if (_columnIndexOfApplicationType == -1) {
      _tmpApplicationType = null;
    } else {
      if (statement.isNull(_columnIndexOfApplicationType)) {
        _tmpApplicationType = null;
      } else {
        _tmpApplicationType = statement.getText(_columnIndexOfApplicationType);
      }
    }
    final String _tmpApplicationStatus;
    if (_columnIndexOfApplicationStatus == -1) {
      _tmpApplicationStatus = null;
    } else {
      if (statement.isNull(_columnIndexOfApplicationStatus)) {
        _tmpApplicationStatus = null;
      } else {
        _tmpApplicationStatus = statement.getText(_columnIndexOfApplicationStatus);
      }
    }
    final boolean _tmpIsArchived;
    if (_columnIndexOfIsArchived == -1) {
      _tmpIsArchived = false;
    } else {
      final int _tmp;
      _tmp = (int) (statement.getLong(_columnIndexOfIsArchived));
      _tmpIsArchived = _tmp != 0;
    }
    final String _tmpNotes;
    if (_columnIndexOfNotes == -1) {
      _tmpNotes = null;
    } else {
      if (statement.isNull(_columnIndexOfNotes)) {
        _tmpNotes = null;
      } else {
        _tmpNotes = statement.getText(_columnIndexOfNotes);
      }
    }
    final String _tmpSyncHash;
    if (_columnIndexOfSyncHash == -1) {
      _tmpSyncHash = null;
    } else {
      if (statement.isNull(_columnIndexOfSyncHash)) {
        _tmpSyncHash = null;
      } else {
        _tmpSyncHash = statement.getText(_columnIndexOfSyncHash);
      }
    }
    final boolean _tmpIsDeleted;
    if (_columnIndexOfIsDeleted == -1) {
      _tmpIsDeleted = false;
    } else {
      final int _tmp_1;
      _tmp_1 = (int) (statement.getLong(_columnIndexOfIsDeleted));
      _tmpIsDeleted = _tmp_1 != 0;
    }
    final long _tmpCreatedAt;
    if (_columnIndexOfCreatedAt == -1) {
      _tmpCreatedAt = 0;
    } else {
      _tmpCreatedAt = statement.getLong(_columnIndexOfCreatedAt);
    }
    final long _tmpUpdatedAt;
    if (_columnIndexOfUpdatedAt == -1) {
      _tmpUpdatedAt = 0;
    } else {
      _tmpUpdatedAt = statement.getLong(_columnIndexOfUpdatedAt);
    }
    final Long _tmpDeletedAt;
    if (_columnIndexOfDeletedAt == -1) {
      _tmpDeletedAt = null;
    } else {
      if (statement.isNull(_columnIndexOfDeletedAt)) {
        _tmpDeletedAt = null;
      } else {
        _tmpDeletedAt = statement.getLong(_columnIndexOfDeletedAt);
      }
    }
    final Long _tmpArchivedAt;
    if (_columnIndexOfArchivedAt == -1) {
      _tmpArchivedAt = null;
    } else {
      if (statement.isNull(_columnIndexOfArchivedAt)) {
        _tmpArchivedAt = null;
      } else {
        _tmpArchivedAt = statement.getLong(_columnIndexOfArchivedAt);
      }
    }
    _entity = new ApplicationEntity(_tmpId,_tmpTitle,_tmpUserId,_tmpCompanyId,_tmpApplicationDate,_tmpPlatform,_tmpContractType,_tmpLocation,_tmpApplicationType,_tmpApplicationStatus,_tmpIsArchived,_tmpNotes,_tmpSyncHash,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
    return _entity;
  }
}
