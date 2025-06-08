package com.delhomme.jobbingtrack.data.local.dao.cv;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.delhomme.jobbingtrack.data.local.entities.cv.ProfilEntity;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
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
public final class ProfileDao_Impl implements ProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<ProfilEntity> __insertAdapterOfProfileEntity;

  private final EntityDeleteOrUpdateAdapter<ProfilEntity> __updateAdapterOfProfileEntity;

  public ProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfProfileEntity = new EntityInsertAdapter<ProfilEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `profiles` (`id`,`userId`,`subject`,`companyIds`,`contactIds`,`candidatureIds`,`relanceIds`,`notes`,`syncHash`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ProfilEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUserId());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getSubject());
        }
        if (entity.getCompanyIds() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCompanyIds());
        }
        if (entity.getContactIds() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getContactIds());
        }
        if (entity.getApplicationIds() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getApplicationIds());
        }
        if (entity.getFollowUpIds() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getFollowUpIds());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(10, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getArchivedAt());
        }
      }
    };
    this.__updateAdapterOfProfileEntity = new EntityDeleteOrUpdateAdapter<ProfilEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `profiles` SET `id` = ?,`userId` = ?,`subject` = ?,`companyIds` = ?,`contactIds` = ?,`candidatureIds` = ?,`relanceIds` = ?,`notes` = ?,`syncHash` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ProfilEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUserId());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getSubject());
        }
        if (entity.getCompanyIds() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCompanyIds());
        }
        if (entity.getContactIds() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getContactIds());
        }
        if (entity.getApplicationIds() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getApplicationIds());
        }
        if (entity.getFollowUpIds() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getFollowUpIds());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(10, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final ProfilEntity profile, final Continuation<? super Unit> $completion) {
    if (profile == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfProfileEntity.insert(_connection, profile);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final ProfilEntity profile, final Continuation<? super Unit> $completion) {
    if (profile == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfProfileEntity.handle(_connection, profile);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<ProfilEntity>> getAll() {
    final String _sql = "SELECT * FROM profiles ORDER BY id";
    return FlowUtil.createFlow(__db, false, new String[] {"profiles"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfSubject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subject");
        final int _columnIndexOfCompanyIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyIds");
        final int _columnIndexOfContactIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactIds");
        final int _columnIndexOfCandidatureIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureIds");
        final int _columnIndexOfRelanceIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relanceIds");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ProfilEntity> _result = new ArrayList<ProfilEntity>();
        while (_stmt.step()) {
          final ProfilEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpSubject;
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null;
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject);
          }
          final String _tmpCompanyIds;
          if (_stmt.isNull(_columnIndexOfCompanyIds)) {
            _tmpCompanyIds = null;
          } else {
            _tmpCompanyIds = _stmt.getText(_columnIndexOfCompanyIds);
          }
          final String _tmpContactIds;
          if (_stmt.isNull(_columnIndexOfContactIds)) {
            _tmpContactIds = null;
          } else {
            _tmpContactIds = _stmt.getText(_columnIndexOfContactIds);
          }
          final String _tmpCandidatureIds;
          if (_stmt.isNull(_columnIndexOfCandidatureIds)) {
            _tmpCandidatureIds = null;
          } else {
            _tmpCandidatureIds = _stmt.getText(_columnIndexOfCandidatureIds);
          }
          final String _tmpRelanceIds;
          if (_stmt.isNull(_columnIndexOfRelanceIds)) {
            _tmpRelanceIds = null;
          } else {
            _tmpRelanceIds = _stmt.getText(_columnIndexOfRelanceIds);
          }
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
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
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
          _item = new ProfilEntity(_tmpId,_tmpUserId,_tmpSubject,_tmpCompanyIds,_tmpContactIds,_tmpCandidatureIds,_tmpRelanceIds,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<ProfilEntity> getById(final String id) {
    final String _sql = "SELECT * FROM profiles WHERE id = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"profiles"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfSubject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subject");
        final int _columnIndexOfCompanyIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyIds");
        final int _columnIndexOfContactIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactIds");
        final int _columnIndexOfCandidatureIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureIds");
        final int _columnIndexOfRelanceIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relanceIds");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ProfilEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpSubject;
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null;
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject);
          }
          final String _tmpCompanyIds;
          if (_stmt.isNull(_columnIndexOfCompanyIds)) {
            _tmpCompanyIds = null;
          } else {
            _tmpCompanyIds = _stmt.getText(_columnIndexOfCompanyIds);
          }
          final String _tmpContactIds;
          if (_stmt.isNull(_columnIndexOfContactIds)) {
            _tmpContactIds = null;
          } else {
            _tmpContactIds = _stmt.getText(_columnIndexOfContactIds);
          }
          final String _tmpCandidatureIds;
          if (_stmt.isNull(_columnIndexOfCandidatureIds)) {
            _tmpCandidatureIds = null;
          } else {
            _tmpCandidatureIds = _stmt.getText(_columnIndexOfCandidatureIds);
          }
          final String _tmpRelanceIds;
          if (_stmt.isNull(_columnIndexOfRelanceIds)) {
            _tmpRelanceIds = null;
          } else {
            _tmpRelanceIds = _stmt.getText(_columnIndexOfRelanceIds);
          }
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
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
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
          _result = new ProfilEntity(_tmpId,_tmpUserId,_tmpSubject,_tmpCompanyIds,_tmpContactIds,_tmpCandidatureIds,_tmpRelanceIds,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
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
  public Object getByIdNow(final String id, final Continuation<? super ProfilEntity> $completion) {
    final String _sql = "SELECT * FROM profiles WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfSubject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subject");
        final int _columnIndexOfCompanyIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyIds");
        final int _columnIndexOfContactIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactIds");
        final int _columnIndexOfCandidatureIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureIds");
        final int _columnIndexOfRelanceIds = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relanceIds");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ProfilEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpSubject;
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null;
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject);
          }
          final String _tmpCompanyIds;
          if (_stmt.isNull(_columnIndexOfCompanyIds)) {
            _tmpCompanyIds = null;
          } else {
            _tmpCompanyIds = _stmt.getText(_columnIndexOfCompanyIds);
          }
          final String _tmpContactIds;
          if (_stmt.isNull(_columnIndexOfContactIds)) {
            _tmpContactIds = null;
          } else {
            _tmpContactIds = _stmt.getText(_columnIndexOfContactIds);
          }
          final String _tmpCandidatureIds;
          if (_stmt.isNull(_columnIndexOfCandidatureIds)) {
            _tmpCandidatureIds = null;
          } else {
            _tmpCandidatureIds = _stmt.getText(_columnIndexOfCandidatureIds);
          }
          final String _tmpRelanceIds;
          if (_stmt.isNull(_columnIndexOfRelanceIds)) {
            _tmpRelanceIds = null;
          } else {
            _tmpRelanceIds = _stmt.getText(_columnIndexOfRelanceIds);
          }
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
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
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
          _result = new ProfilEntity(_tmpId,_tmpUserId,_tmpSubject,_tmpCompanyIds,_tmpContactIds,_tmpCandidatureIds,_tmpRelanceIds,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM profiles WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM profiles";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
